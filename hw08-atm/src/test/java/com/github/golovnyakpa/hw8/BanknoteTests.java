package com.github.golovnyakpa.hw8;

import com.github.golovnyakpa.hw8.banknotes.Banknote;
import com.github.golovnyakpa.hw8.banknotes.RusBanknote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BanknoteTests {

    @Test
    public void testRusBanknoteNominal() {
        Banknote tenRubles = RusBanknote.TEN;
        Banknote hundredRubles = RusBanknote.HUNDRED;
        Banknote thousandRubles = RusBanknote.THOUSAND;

        Assertions.assertEquals(10, tenRubles.getNominal());
        Assertions.assertEquals(100, hundredRubles.getNominal());
        Assertions.assertEquals(1000, thousandRubles.getNominal());
    }

}
