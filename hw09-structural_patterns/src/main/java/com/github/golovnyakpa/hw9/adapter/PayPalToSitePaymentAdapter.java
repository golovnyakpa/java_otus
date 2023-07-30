package com.github.golovnyakpa.hw9.adapter;

import com.github.golovnyakpa.hw9.adapter.payment_sytems.PayPal;

public class PayPalToSitePaymentAdapter implements SitePayment {

    private final PayPal payPal;

    public PayPalToSitePaymentAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    @Override
    public void runPayment(int amount) {
        System.out.println("Switching on adapter for pay pal payment at site...");
        payPal.runPayPalPayment(amount);
        System.out.println("Payment done");
    }
}
