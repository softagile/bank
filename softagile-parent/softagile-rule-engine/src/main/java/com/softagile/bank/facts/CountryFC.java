package com.softagile.bank.facts;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author bkalali
 *
 */
public class CountryFC {
    private String name;
    private boolean valid = true;
    private List<String> errorMessages = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void addErrorMessage(String error) {
        errorMessages.add(error);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
