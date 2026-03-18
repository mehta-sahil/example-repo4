package com.example.integrations.stripe;

import java.util.HashMap;
import java.util.Map;

/**
 * Stripe integration client.
 * WARNING: Uses deprecated Charges API (removed in Stripe API v12).
 * Should be migrated to PaymentIntents API.
 */
public class StripeClient {

    private static final String STRIPE_API_URL = "https://api.stripe.com/v1/charges";
    private final String apiKey;

    public StripeClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Create a charge using the legacy Charges API.
     * DEPRECATED: Stripe removed this in favour of PaymentIntents.
     */
    public Map<String, Object> createCharge(int amountInCents, String currency, String source) {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amountInCents);
        params.put("currency", currency);
        params.put("source", source);
        params.put("description", "Charge via legacy API");

        // POST to /v1/charges
        System.out.println("POST " + STRIPE_API_URL);
        System.out.println("amount=" + amountInCents + ", currency=" + currency);

        // Simulated response
        Map<String, Object> response = new HashMap<>();
        response.put("id", "ch_123456789");
        response.put("object", "charge");
        response.put("amount", amountInCents);
        response.put("currency", currency);
        response.put("status", "succeeded");
        return response;
    }

    /**
     * Retrieve a charge by ID.
     * DEPRECATED: Use PaymentIntent retrieval instead.
     */
    public Map<String, Object> retrieveCharge(String chargeId) {
        String url = STRIPE_API_URL + "/" + chargeId;
        System.out.println("GET " + url);

        Map<String, Object> response = new HashMap<>();
        response.put("id", chargeId);
        response.put("object", "charge");
        response.put("status", "succeeded");
        return response;
    }

    /**
     * Refund a charge using the old refunds endpoint.
     */
    public Map<String, Object> refundCharge(String chargeId) {
        String url = "https://api.stripe.com/v1/refunds";
        Map<String, Object> params = new HashMap<>();
        params.put("charge", chargeId);

        System.out.println("POST " + url + " charge=" + chargeId);

        Map<String, Object> response = new HashMap<>();
        response.put("id", "re_123456789");
        response.put("object", "refund");
        response.put("status", "succeeded");
        return response;
    }
}
