package com.github.golovnyakpa.hw4.utils;

import com.github.golovnyakpa.hw4.test_entities.TestResult;

import java.util.List;

public class StatsPrinter {
    public static void print(List<String> aborted, List<String> failed, List<TestResult> succeed) {
        System.out.println("-".repeat(70));
        printTotalStats(aborted, failed, succeed);
        if (!failed.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Failed tests: " + String.join(", ", failed));
        }
        if (!aborted.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Aborted tests: " + String.join(", ", aborted));
        }
        System.out.println(ConsoleColors.RESET + "-".repeat(70));
    }

    private static void printTotalStats(List<String> aborted, List<String> failed, List<TestResult> succeed) {
        System.out.printf(
                ConsoleColors.BLUE + "Total number of tests: %d\n",
                aborted.size() + failed.size() + succeed.size()
        );
        System.out.println("Number of succeed tests: " + succeed.size());
        System.out.println("Number of failed tests: " + failed.size());
        System.out.println("Number of aborted tests: " + aborted.size());
    }
}
