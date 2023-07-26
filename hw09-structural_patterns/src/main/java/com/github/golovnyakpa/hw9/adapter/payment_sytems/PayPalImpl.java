package com.github.golovnyakpa.hw9.adapter.payment_sytems;

public class PayPalImpl implements PayPal {
    @Override
    public void runPayPalPayment(int amount) {
        System.out.printf("Running %s dollars pay pal payment%n", amount);
    }
}
