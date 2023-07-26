package com.github.golovnyakpa.hw8;

import com.github.golovnyakpa.hw8.atm.AtmImpl;
import com.github.golovnyakpa.hw8.banknotes.Banknote;
import com.github.golovnyakpa.hw8.banknotes.RusBanknote;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AtmImpl atm = new AtmImpl();
        List<Banknote> banknotes = Arrays.asList(RusBanknote.THOUSAND, RusBanknote.FV_THOUSAND, RusBanknote.FV_THOUSAND);
        atm.acceptBanknotes(banknotes);
        System.out.println(atm.showCurrentAmount());
        atm.giveAmount(5000);
        System.out.println(atm.showCurrentAmount());
    }
}
