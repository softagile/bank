package com.softagile.bank.client;

import java.util.UUID;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class HttpHeaders {

    public static void createHttpHeadersForCXFRestClient(WebClient client) {
        client.headers(getHttpHeaders());
    }

    public static MultivaluedMap<String, String> getHttpHeaders() {
        MultivaluedMap<String, String> headers = new MetadataMap<String, String>();
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        String originationTimestamp = fmt.print(dt);
        headers.add("SERVICE_ENV_FIELD", "DEV");
        headers.add("SERVICE_FUNC_AREA_FIELD", "PE");
        headers.add("TRACKING_ID_FIELD", randomUUIDString);
        headers.add("ORIG_TIMESTAMP_FIELD", originationTimestamp);
        headers.add("SRC_PROGRAM_FIELD", "HIPPO_CMS");
        headers.add("HEADER_VERSION_FIELD", "1.1.0");
        headers.add("validation", "no");
        return headers;
    }
}
