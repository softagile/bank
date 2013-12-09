package com.softagile.bank.exception;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class RetryManager.
 * 
 * @author bkalali
 */
//TODO BK this will be moved to its own module
public class RetryManager {

    private Logger logger = (Logger) LoggerFactory.getLogger(RetryManager.class);

    private boolean isDone = false;

    private int retries = 3;

    private int retriedSoFar = 0;

    private int sleepMsBetweenRetries = 10000;

    public RetryManager(int retries, int sleepMsBetweenRetries) {
        this.retries = retries;
        this.sleepMsBetweenRetries = sleepMsBetweenRetries;
        this.retriedSoFar = 0;
    }

    public void checkIfExceptionIsValidForNextRetry(boolean retriable) {
        if (retriable) {
            waitAndThenIncrementNumberOfRetriedSoFar();
        } else {
            terminate();
        }
    }

    public boolean shouldContinue() {
        return !isDone && this.retriedSoFar < this.retries;
    }

    public void done() {
        isDone = true;
    }

    private void terminate() {
        isDone = true;
    }

    private void waitAndThenIncrementNumberOfRetriedSoFar() {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepMsBetweenRetries);
            incrementNumberOfRetriedSoFar();
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }

    }

    private void incrementNumberOfRetriedSoFar() {
        retriedSoFar++;
    }
}
