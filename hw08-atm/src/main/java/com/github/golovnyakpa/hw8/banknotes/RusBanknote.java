package com.github.golovnyakpa.hw8.banknotes;

public enum RusBanknote implements Banknote {
    TEN(10), FIFTY(50), HUNDRED(100), FV_HUNDRED(500), THOUSAND(1000), FV_THOUSAND(5000);

    private final int nominal;

    RusBanknote(int nominal) {
        this.nominal = nominal;
    }

    @Override
    public int getNominal() {
        return nominal;
    }
}
