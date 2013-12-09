package com.softagile.bank.api.service;

import com.softagile.bank.requestreply.CountryReply;
import com.softagile.bank.requestreply.PanelReply;

public interface PanelExcellenceService {

	//Things to consider: if there are more than 4 parameters, create Request Object
	//If the Reply has an object graph structure, then a dto created and encapsulated in Reply
	public PanelReply getPanelReplyByCountryCode(String countryCode);
	
	public CountryReply getCountryReplyByCounytryCode(String countryCode);
	
	public CountryReply createCountry(String countryName);
}
