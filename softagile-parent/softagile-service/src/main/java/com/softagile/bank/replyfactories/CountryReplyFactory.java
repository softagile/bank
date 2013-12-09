package com.softagile.bank.replyfactories;

import com.softagile.bank.domain.Country;
import com.softagile.bank.requestreply.CountryReply;

public class CountryReplyFactory {

    public static CountryReply createCountryReply(Country country) {
        CountryReply countryReply = new CountryReply();
        countryReply.setIsoCountryCode(country.getIsoCountryCode());
        return countryReply;

    }
}
