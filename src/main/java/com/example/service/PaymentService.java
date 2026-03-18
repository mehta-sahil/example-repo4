package com.example.service;

import com.example.integrations.stripe.StripeClient;
import com.example.integrations.twilio.TwilioClient;
import java.util.Map;

/**
 * Payment and notification service.
 * Orchestrates Stripe payments and Twilio SMS notifications.
 */
public class PaymentService {

    private final StripeClient stripeClient;
    private final TwilioClient twilioClient;

    private static final String TWILIO_FROM_NUMBER = "+15551234567";

    public PaymentService(String stripeKey, String twilioSid, String twilioToken) {
        this.stripeClient = new StripeClient(stripeKey);
        this.twilioClient = new TwilioClient(twilioSid, twilioToken);
    }

    /**
     * Process a payment and notify the customer via SMS.
     * Uses legacy Stripe Charges API + Twilio Messages API.
     */
    public String processPaymentAndNotify(int amountCents, String currency,
                                           String cardSource, String customerPhone) {
        // Step 1: Charge the card using old Charges API
        Map<String, Object> charge = stripeClient.createCharge(amountCents, currency, cardSource);
        String chargeId = (String) charge.get("id");
        String status = (String) charge.get("status");

        System.out.println("Charge created: " + chargeId + " status=" + status);

        // Step 2: Send SMS confirmation using legacy Twilio endpoint
        if ("succeeded".equals(status)) {
            String message = "Payment of " + (amountCents / 100.0) + " "
                    + currency.toUpperCase() + " received. Ref: " + chargeId;
            twilioClient.sendSms(TWILIO_FROM_NUMBER, customerPhone, message);
        }

        return chargeId;
    }

    /**
     * Refund a payment and notify the customer.
     */
    public void refundAndNotify(String chargeId, String customerPhone) {
        // Using old refund API
        Map<String, Object> refund = stripeClient.refundCharge(chargeId);
        String refundId = (String) refund.get("id");

        String message = "Your refund has been processed. Ref: " + refundId;
        twilioClient.sendSms(TWILIO_FROM_NUMBER, customerPhone, message);

        System.out.println("Refund processed: " + refundId);
    }
}
