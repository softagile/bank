package com.softagile.bank.requestreply;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countryReply", namespace = "http://www.softagile.com/schema/bank/reply", propOrder = { "countryCode" })
public class CountryReply extends ReplyStatus implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 4045567768940836396L;

    @XmlElement(namespace = "http://www.softagile.com/schema/bank/reply", required = true)
    private String isoCountryCode;

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

}
