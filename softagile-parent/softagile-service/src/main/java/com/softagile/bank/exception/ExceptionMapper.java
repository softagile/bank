package com.softagile.bank.exception;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

import com.softagile.bank.rule.engine.PanelExcellenceValidationException;

/**
 * The Class ExceptionMapper.
 * 
 * @author bkalali
 */
@Component
public class ExceptionMapper {

    private static boolean NON_RETRIABLE = false;
    private static boolean RETRIABLE = true;

    private Map<Class<?>, ExceptionWrapper> exceptionWrappersIndexedByExceptionTypes = new HashMap<Class<?>, ExceptionWrapper>();

    @PostConstruct
    protected void loadExceptionClasses() {

        // non-retriable
        exceptionWrappersIndexedByExceptionTypes.put(NonTransientDataAccessException.class, new ExceptionWrapper(
            NON_RETRIABLE));
       
        // non-retriable
        exceptionWrappersIndexedByExceptionTypes.put(JpaObjectRetrievalFailureException.class, new ExceptionWrapper(
            NON_RETRIABLE));
        
        // re-triable
        exceptionWrappersIndexedByExceptionTypes.put(RecoverableDataAccessException.class, new ExceptionWrapper(
            RETRIABLE));

        // re-triable
        exceptionWrappersIndexedByExceptionTypes.put(OptimisticLockingFailureException.class, new ExceptionWrapper(
            RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(PessimisticLockingFailureException.class, new ExceptionWrapper(
            RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(CannotAcquireLockException.class, new ExceptionWrapper(RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(DeadlockLoserDataAccessException.class, new ExceptionWrapper(
            RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(QueryTimeoutException.class, new ExceptionWrapper(RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(TransientDataAccessResourceException.class, new ExceptionWrapper(
            RETRIABLE));

        // non-retriable
        exceptionWrappersIndexedByExceptionTypes.put(DataAccessException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(DataRetrievalFailureException.class, new ExceptionWrapper(
            NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(InvalidDataAccessResourceUsageException.class,
            new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(EntityNotFoundException.class,
            new ExceptionWrapper(NON_RETRIABLE));
        // non-retriable
        exceptionWrappersIndexedByExceptionTypes.put(ValidationException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(CannotCreateTransactionException.class, new ExceptionWrapper(NON_RETRIABLE));
        
        // non-retriable
        exceptionWrappersIndexedByExceptionTypes
            .put(JsonGenerationException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(JsonMappingException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(IOException.class, new ExceptionWrapper(NON_RETRIABLE));

        // non-retriable
        exceptionWrappersIndexedByExceptionTypes.put(InstantiationException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(IllegalAccessException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(ClassNotFoundException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(NoSuchMethodException.class, new ExceptionWrapper(NON_RETRIABLE));
        exceptionWrappersIndexedByExceptionTypes.put(InvocationTargetException.class, new ExceptionWrapper(
            NON_RETRIABLE));
        
        exceptionWrappersIndexedByExceptionTypes.put(PanelExcellenceValidationException.class, new ExceptionWrapper(
                NON_RETRIABLE));
        
        exceptionWrappersIndexedByExceptionTypes.put(NullPointerException.class, new ExceptionWrapper(
            NON_RETRIABLE));
        
        exceptionWrappersIndexedByExceptionTypes.put(MessageHeaderException.class, new ExceptionWrapper(
            NON_RETRIABLE));
    }

    public ExceptionWrapper getExceptionWrapper(Class<? extends Throwable> exceptionClass) {
        return exceptionWrappersIndexedByExceptionTypes.get(exceptionClass);
    }
}
