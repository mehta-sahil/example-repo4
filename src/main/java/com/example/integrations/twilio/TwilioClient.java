package com.example.integrations.twilio;

import java.util.HashMap;
import java.util.Map;

/**
 * Twilio integration client.
 * Uses legacy REST API v2 patterns and deprecated SMS endpoint structure.
 */
public class TwilioClient {

    // Legacy base URL — Twilio updated their API versioning
    private static final String BASE_URL = "https://api.twilio.com/2010-04-01";
    private final String accountSid;
    private final String authToken;

    public TwilioClient(String accountSid, String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    /**
     * Send an SMS using the legacy Messages endpoint.
     * Uses old parameter names: 'From', 'To', 'Body' as direct form fields.
     */
    public Map<String, Object> sendSms(String from, String to, String body) {
        String url = BASE_URL + "/Accounts/" + accountSid + "/Messages.json";

        Map<String, Object> params = new HashMap<>();
        params.put("From", from);
        params.put("To", to);
        params.put("Body", body);

        System.out.println("POST " + url);
        System.out.println("From=" + from + ", To=" + to);

        // Simulated response using old response schema
        Map<String, Object> response = new HashMap<>();
        response.put("sid", "SM123456789");
        response.put("status", "queued");
        response.put("from", from);
        response.put("to", to);
        response.put("body", body);
        // Old field name — Twilio renamed this to 'numSegments' in newer versions
        response.put("num_segments", "1");
        return response;
    }

    /**
     * Make a voice call using deprecated TwiML URL pattern.
     */
    public Map<String, Object> makeCall(String from, String to, String twimlUrl) {
        String url = BASE_URL + "/Accounts/" + accountSid + "/Calls.json";

        Map<String, Object> params = new HashMap<>();
        params.put("From", from);
        params.put("To", to);
        params.put("Url", twimlUrl);
        // Deprecated parameter removed in newer API versions
        params.put("Method", "GET");

        System.out.println("POST " + url);

        Map<String, Object> response = new HashMap<>();
        response.put("sid", "CA123456789");
        response.put("status", "queued");
        response.put("direction", "outbound-api");
        return response;
    }

    /**
     * Fetch account balance using old Accounts endpoint.
     * Deprecated: Twilio moved this to the Accounts v2 API.
     */
    public Map<String, Object> getAccountBalance() {
        String url = BASE_URL + "/Accounts/" + accountSid + ".json";
        System.out.println("GET " + url);

        Map<String, Object> response = new HashMap<>();
        response.put("sid", accountSid);
        response.put("balance", "100.00");
        response.put("currency", "USD");
        return response;
    }
}
