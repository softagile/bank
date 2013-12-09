package com.softagile.bank.exception;

/**
 * The Class ExceptionWrapper.
 * 
 * @author bkalali
 */
//TODO BK this will be moved to its own module
public final class ExceptionWrapper {

    private boolean retriable = false;

    private Throwable throwable;

    public ExceptionWrapper(boolean retriable) {
        this.retriable = retriable;
    }

    public boolean isRetriable() {
        return retriable;
    }

    public void setRetriable(boolean retriable) {
        this.retriable = retriable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
