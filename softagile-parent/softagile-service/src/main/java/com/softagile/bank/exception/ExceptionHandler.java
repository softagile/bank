package com.softagile.bank.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
/**
 * The Interface ExceptionHandler.
 * 
 * @author bkalali
 */
//TODO BK this will be moved to its own module
public @interface ExceptionHandler {

 

}
   