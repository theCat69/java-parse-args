package com.wha.parseargs.argument;

import java.util.function.BiConsumer;

public class IntegerArgument<U> extends Argument<Integer, U> {
    public IntegerArgument(Flag flag, BiConsumer<U, Integer> setter) {
        super(flag, 1, null, setter, (u, s) -> setter.accept(u, Integer.valueOf(s)));
    }

    public IntegerArgument(Flag flag, Integer defaultValue, BiConsumer<U, Integer> setter) {
        super(flag, 1, defaultValue, setter, (u, s) -> setter.accept(u, Integer.valueOf(s)));
    }
}
