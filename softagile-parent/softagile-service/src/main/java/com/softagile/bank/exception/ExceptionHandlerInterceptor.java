package com.softagile.bank.exception;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

/**
 * The Class ExceptionHandlerInterceptor. The Ordered interface enforces that this Aspect has higher priority than
 * transaction manager
 * 
 * @author bkalali
 */
@Aspect
@Service("ExceptionHandlerInterceptor")
// TODO BK this will be moved to its own module
public class ExceptionHandlerInterceptor implements Ordered {

    private Logger logger = (Logger) LoggerFactory.getLogger(ExceptionHandlerInterceptor.class);

    // TODO externalize these three
    private int maxRetries = 3;
    private int sleepMsBetweenRetries = 1000;
    private boolean isLoggingEnabled = true;
    
    @Autowired
    private ExceptionMapper exceptionMapper;

    private int order = 1;

    @Around("@annotation(exceptionHandler)")
    public Object intercept(ProceedingJoinPoint pjp, ExceptionHandler exceptionHandler) throws Throwable {
        RetryManager retryManager = new RetryManager(maxRetries, sleepMsBetweenRetries);
        String method = pjp.getSignature().toShortString();
        logger.info("Service API {} with paramters {} invoked", method, pjp.getArgs());
        ExceptionWrapper exceptionWrapper = null;
        while (retryManager.shouldContinue()) {
            try {
                Object successReply = pjp.proceed();
                logReplyInJson(successReply);
                retryManager.done();
                return successReply;
            } catch (Throwable ex) {
                exceptionWrapper = exceptionMapper.getExceptionWrapper(ex.getClass());
                if (null == exceptionWrapper) {
                    retryManager.done();
                    throw ex;
                } else {
                    exceptionWrapper.setThrowable(ex);
                    retryManager.checkIfExceptionIsValidForNextRetry(exceptionWrapper.isRetriable());
                }
            }
        }
        String[] methodSignature = pjp.getSignature().toLongString().split("\\ ");
        return getFailedReply(methodSignature, exceptionWrapper);
    }

    // TODO Map a exception to a reply object according to business exception
    private Object getFailedReply(String[] methodSignature, ExceptionWrapper exceptionWrapper)
        throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException,
        InvocationTargetException, JsonGenerationException, JsonMappingException, IOException {
        String returnType = methodSignature[1];
        if ("abstract".equals(returnType)) {
            returnType = methodSignature[2];
        }
        Object failedReply = Class.forName(returnType).newInstance();
        populateReplyStatus(exceptionWrapper, failedReply);
        logReplyInJson(failedReply);
        return failedReply;
    }

    private void populateReplyStatus(ExceptionWrapper exceptionWrapper, Object failedReply)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> clazz = failedReply.getClass();
        Method setStatusCode = clazz.getMethod("setStatusCode", String.class);
        Method setDescription = clazz.getMethod("setDescription", String.class);
        Method setRetried = clazz.getMethod("setRetried", boolean.class);
        // TODO later, we will translate to the actual business exception
        setStatusCode.invoke(failedReply, "500");
        setDescription.invoke(failedReply, exceptionWrapper.getThrowable().getMessage());
        if (exceptionWrapper.isRetriable()) {
            setRetried.invoke(failedReply, true);
        }
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    private void logReplyInJson(Object object) throws JsonGenerationException, JsonMappingException, IOException {
        if (isLoggingEnabled) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            logger.info(writer.withDefaultPrettyPrinter().writeValueAsString(object));
        }
    }

}