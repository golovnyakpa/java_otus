package com.github.golovnyakpa.hw8.atm;

import com.github.golovnyakpa.hw8.banknotes.Banknote;

import java.util.List;

public interface Atm {
    void acceptBanknotes(List<Banknote> banknotes);
    void giveAmount(int amount);
    int showCurrentAmount();
}
