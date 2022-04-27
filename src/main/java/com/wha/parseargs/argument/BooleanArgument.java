package com.wha.parseargs.argument;

import java.util.function.BiConsumer;

public class BooleanArgument<U> extends Argument<Boolean, U> {
    public BooleanArgument(Flag flag, Boolean defaultValue, BiConsumer<U, Boolean> setter) {
        super(flag, 0, defaultValue, setter, (u, s) -> setter.accept(u, !defaultValue) );
    }
}
