package com.softagile.bank.requestreply;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "replyStatus", namespace = "http://www.softagile.com/schema/bank/reply", propOrder = { "statusCode",
    "description","retried" })
public class ReplyStatus {

    //TODO BK these statusCode will be enumated later and overridden by status code mapped from runtime exceptions
    @XmlElement(namespace = "http://www.softagile.com/schema/bank/reply", required = true)
    private String statusCode="200";

    @XmlElement(namespace = "http://www.softagile.com/schema/bank/reply", required = true)
    private String description="SUCCESS";

    @XmlElement(namespace = "http://www.softagile.com/schema/bank/reply", required = true)
    private boolean retried = false;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRetried() {
        return retried;
    }

    public void setRetried(boolean retried) {
        this.retried = retried;
    }

}
