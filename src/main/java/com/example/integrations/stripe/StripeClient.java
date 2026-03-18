package com.example.integrations.stripe;

import java.util.HashMap;
import java.util.Map;

/**
 * Stripe integration client.
 * WARNING: This client uses the legacy Stripe Charges API, which has been removed in newer Stripe API versions.
 * All methods in this class are now unsupported and will throw an UnsupportedOperationException.
 * It must be migrated to the PaymentIntents API for continued functionality.
 */
public class StripeClient {

    // This constant refers to the legacy Charges API endpoint, which is no longer supported.
    private static final String STRIPE_API_URL = "https://api.stripe.com/v1/charges";
    private final String apiKey;

    public StripeClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Create a charge using the legacy Charges API.
     * DEPRECATED: Stripe removed this in favour of PaymentIntents.
     * This method is now unsupported and will throw an {@link UnsupportedOperationException}.
     * @deprecated Use the PaymentIntents API for creating payments.
     */
    @Deprecated
    public Map<String, Object> createCharge(int amountInCents, String currency, String source) {
        throw new UnsupportedOperationException(
            "The legacy Charges API (v1/charges) has been removed. Please migrate to the PaymentIntents API."
        );
    }

    /**
     * Retrieve a charge by ID.
     * DEPRECATED: Use PaymentIntent retrieval instead.
     * This method is now unsupported and will throw an {@link UnsupportedOperationException}.
     * @deprecated Use the PaymentIntents API for retrieving payments.
     */
    @Deprecated
    public Map<String, Object> retrieveCharge(String chargeId) {
        throw new UnsupportedOperationException(
            "The legacy Charges API (v1/charges) has been removed. Please migrate to the PaymentIntents API."
        );
    }

    /**
     * Refund a charge using the old refunds endpoint.
     * This method is now unsupported as it relies on the deprecated Charges API for its context.
     * It will throw an {@link UnsupportedOperationException}.
     * @deprecated Use the PaymentIntents API for managing refunds.
     */
    @Deprecated
    public Map<String, Object> refundCharge(String chargeId) {
        // Even though /v1/refunds might exist, its usage tied to legacy charge IDs is problematic
        // and the correct way to refund is now via PaymentIntents.
        throw new UnsupportedOperationException(
            "The legacy Charges API and its associated refund mechanism have been removed. Please migrate to the PaymentIntents API for refunds."
        );
    }
}
