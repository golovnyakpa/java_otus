package com.github.golovnyakpa.hw8;

import com.github.golovnyakpa.hw8.atm.AtmImpl;
import com.github.golovnyakpa.hw8.banknotes.Banknote;
import com.github.golovnyakpa.hw8.banknotes.RusBanknote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmTests {

    @Test
    public void testAcceptBanknotes() {
        AtmImpl atm = new AtmImpl();
        List<Banknote> banknotes = Arrays.asList(RusBanknote.TEN, RusBanknote.HUNDRED, RusBanknote.HUNDRED);
        atm.acceptBanknotes(banknotes);
        Assertions.assertEquals(210, atm.showCurrentAmount()); // 10 + 100 + 100 = 210
        atm.acceptBanknotes(Arrays.asList(RusBanknote.THOUSAND, RusBanknote.HUNDRED));
        Assertions.assertEquals(1310, atm.showCurrentAmount()); // 210 + 1000 + 100 = 1310
        atm.acceptBanknotes(Arrays.asList(RusBanknote.FV_HUNDRED, RusBanknote.TEN));
        Assertions.assertEquals(1820, atm.showCurrentAmount()); // 1310 + 500 + 10 = 1820

        Map<Banknote, Integer> atmInitState = new HashMap<>();
        atmInitState.put(RusBanknote.THOUSAND, 2);
        atmInitState.put(RusBanknote.FIFTY, 2);
        AtmImpl atm1 = new AtmImpl(atmInitState);
        Assertions.assertEquals(2100, atm1.showCurrentAmount());
        atm1.acceptBanknotes(List.of(RusBanknote.FV_HUNDRED));
        Assertions.assertEquals(2600, atm1.showCurrentAmount());
    }

    @Test
    public void testGiveAmount() {
        AtmImpl atm = new AtmImpl();
        List<Banknote> banknotes = Arrays.asList(RusBanknote.TEN, RusBanknote.HUNDRED, RusBanknote.HUNDRED);
        atm.acceptBanknotes(banknotes);
        atm.giveAmount(100);
        Assertions.assertEquals(110, atm.showCurrentAmount());
        atm.giveAmount(10); // 100(1) remains
        Assertions.assertEquals(100, atm.showCurrentAmount());

        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(42));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(50));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(10));

        List<Banknote> moreBanknotes = Arrays.asList(RusBanknote.THOUSAND, RusBanknote.FV_HUNDRED);
        atm.acceptBanknotes(moreBanknotes); // 100(1), 500(1), 1000(1)
        atm.giveAmount(500);
        Assertions.assertEquals(1100, atm.showCurrentAmount());
        atm.giveAmount(1000);
        Assertions.assertEquals(100, atm.showCurrentAmount()); // 100 (1) remains

        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(500));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(1000));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm.giveAmount(50));

        Map<Banknote, Integer> atmInitState = new HashMap<>();
        atmInitState.put(RusBanknote.THOUSAND, 2);
        atmInitState.put(RusBanknote.FIFTY, 2);
        AtmImpl atm1 = new AtmImpl(atmInitState);

        Assertions.assertThrows(IllegalArgumentException.class, () -> atm1.giveAmount(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm1.giveAmount(500));
        Assertions.assertThrows(IllegalArgumentException.class, () -> atm1.giveAmount(-1));

        Assertions.assertEquals(2100, atm1.showCurrentAmount());
        atm1.giveAmount(1000);
        Assertions.assertEquals(1100, atm1.showCurrentAmount());
    }
}
