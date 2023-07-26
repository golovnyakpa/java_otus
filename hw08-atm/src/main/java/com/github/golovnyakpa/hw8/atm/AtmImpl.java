package com.github.golovnyakpa.hw8.atm;

import com.github.golovnyakpa.hw8.banknotes.Banknote;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmImpl implements Atm {
    Map<Banknote, Integer> banknoteNums;

    public AtmImpl(Map<Banknote, Integer> banknoteNums) {
        this.banknoteNums = banknoteNums;
    }

    public AtmImpl() {
        this.banknoteNums = new HashMap<>();
    }

    @Override
    public void acceptBanknotes(List<Banknote> banknotes) {
        Map<Banknote, Integer> countedBanknotes = countBanknotes(banknotes);
        updateBanknotesNum(countedBanknotes);
    }

    @Override
    public void giveAmount(int amount) {
        List<Banknote> sortedBanknotes = getSortedBanknotes();
        WithdrawalResult withdrawalResult = getWithdrawalResult(amount, sortedBanknotes);

        if (withdrawalResult.remainingAmount == 0) {
            banknoteNums = getUpdatedBanknotesNum(withdrawalResult);
        } else {
            throw new IllegalArgumentException("Insufficient funds in the ATM to fulfill the requested amount.");
        }
    }

    @Override
    public int showCurrentAmount() {
        return banknoteNums.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getNominal() * entry.getValue())
                .sum();
    }

    private Map<Banknote, Integer> getUpdatedBanknotesNum(WithdrawalResult withdrawalResult) {
        Map<Banknote, Integer> updatedBanknotesNum = new HashMap<>(banknoteNums);
        for (Map.Entry<Banknote, Integer> entry : withdrawalResult.withdrawalBanknotes.entrySet()) {
            Banknote banknote = entry.getKey();
            int count = banknoteNums.getOrDefault(banknote, 0);
            updatedBanknotesNum.put(banknote, count - entry.getValue());
        }
        return updatedBanknotesNum;
    }

    static class WithdrawalResult {
        final Map<Banknote, Integer> withdrawalBanknotes;
        final int remainingAmount;

        public WithdrawalResult(Map<Banknote, Integer> withdrawalBanknotes, int remainingAmount) {
            this.withdrawalBanknotes = withdrawalBanknotes;
            this.remainingAmount = remainingAmount;
        }
    }

    private WithdrawalResult getWithdrawalResult(int requestedAmount, @NotNull List<Banknote> sortedBanknotes) {

        Map<Banknote, Integer> withdrawalBanknotes = new HashMap<>();
        int remainingAmount = requestedAmount;

        for (Banknote banknote : sortedBanknotes) {
            int banknoteNominal = banknote.getNominal();
            int banknoteCount = banknoteNums.getOrDefault(banknote, 0);
            int numOfBanknotesToWithdrawal = Math.min(remainingAmount / banknoteNominal, banknoteCount);

            withdrawalBanknotes.put(banknote, numOfBanknotesToWithdrawal);
            remainingAmount -= numOfBanknotesToWithdrawal * banknoteNominal;

            if (remainingAmount == 0) {
                break;
            }
        }
        return new WithdrawalResult(withdrawalBanknotes, remainingAmount);
    }

    private List<Banknote> getSortedBanknotes() {
        return banknoteNums.keySet().stream()
                .sorted(Comparator.comparingInt(Banknote::getNominal).reversed())
                .toList();
    }

    private @NotNull Map<Banknote, Integer> countBanknotes(@NotNull List<Banknote> banknotes) {
        Map<Banknote, Integer> resultMap = new HashMap<>();

        for (Banknote el : banknotes) {
            resultMap.put(el, resultMap.getOrDefault(el, 0) + 1);
        }
        return resultMap;
    }

    private void updateBanknotesNum(@NotNull Map<Banknote, Integer> countedBanknotes) {
        for (Map.Entry<Banknote, Integer> entry : countedBanknotes.entrySet()) {
            banknoteNums.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }
}
