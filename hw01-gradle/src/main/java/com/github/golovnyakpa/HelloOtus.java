package com.github.golovnyakpa;

import com.google.common.base.Optional;

public class HelloOtus {
    public static void main(String[] args) {
        Optional<String> opt = Optional.of("Monads");
        Optional<String> transformedOpt = opt.transform((s) -> (s + " rulezzzz!"));
        System.out.println(transformedOpt.get());
    }
}