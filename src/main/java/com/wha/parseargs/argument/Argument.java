package com.wha.parseargs.argument;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.BiConsumer;

@AllArgsConstructor
@Getter
public class Argument<T, U> {

    private final Flag flag;
    private final Integer argNb;
    private final T defaultValue;
    private final BiConsumer<U, T> setter;
    private final BiConsumer<U, String> setValueToTarget;

    public void setDefaultValueToTarget(U target) {
        this.setter.accept(target, defaultValue);
    }
}
