package com.softagile.bank.requestreply;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "panelReply", namespace = "http://www.softagile.com/schema/bank/reply", propOrder = { "countryCode",
    "panelIds" })

public class PanelReply extends ReplyStatus implements Serializable {

    @XmlTransient
    private static final long serialVersionUID = 4045567768940836396L;

    @XmlElement(namespace = "http://www.softagile.com/schema/bank/reply", required = true)
    private String countryCode;
    @XmlElement(namespace = "http://www.softagile.com/schema/bank/reply")
    private List<Long> panelIds;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<Long> getPanelIds() {
        return panelIds;
    }

    public void setPanelIds(List<Long> panelIds) {
        this.panelIds = panelIds;
    }

}
