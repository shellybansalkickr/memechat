package com.kickr.memechat.recharge.codaPayment;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodaRestUtil {
    private static final Logger log = LoggerFactory.getLogger(CodaRestUtil.class);
    private static Properties properties = null;
    private static String airtimeRestURL = null;
    private static String airtimeURL = null;
    private static String airtimeHost = null;
    private static String apiKey = null;
    private static JSONObject countries =null;
    private static short paymentType = 0;
    private static String requestType = "json";

    public static String getAirtimeURL () {
        return airtimeURL;
    }

    static {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(CodaRestUtil.class.getResourceAsStream("/coda.properties"));

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (properties != null) {

                try {
                    countries = new JSONObject(properties.getProperty("airtime.countries"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                log.info("AIRTIME CONFIG COUNTRY/CURRENCY : " + countries);

                airtimeRestURL = properties.getProperty("airtime.rest.url");
                log.info("AIRTIME CONFIG REST URL : " + airtimeRestURL);

                airtimeHost = properties.getProperty("airtime.host");
                log.info("AIRTIME CONFIG Host URL : " + airtimeHost);

                airtimeURL = properties.getProperty("airtime.rest.url");
                log.info("AIRTIME CONFIG URL : " + airtimeURL);

                apiKey = properties.getProperty("airtime.apikey");
                log.info("AIRTIME CONFIG API_KEY : " + apiKey);

                if (properties.getProperty("airtime.requesttype") != null) {
                    requestType = properties.getProperty("airtime.requesttype");
                }
                log.info("AIRTIME CONFIG REQUEST TYPE : " + requestType);

                paymentType = Short.parseShort(properties.getProperty("airtime.txntype"));

            }
        }
    }

    public static String getApiKey () {
        return apiKey;
    }


    public static short getPaymentType () {
        return paymentType;
    }

    public static String getAirtimeHost() {
        return airtimeHost;
    }

    public static void setAirtimeHost(String airtimeHost) {
        CodaRestUtil.airtimeHost = airtimeHost;
    }

    public static JSONObject getCountry() {
        return countries;
    }

    public static void setCountry(JSONObject country) {
        CodaRestUtil.countries = country;
    }
}
