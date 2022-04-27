package com.wha.parseargs.argument;

import java.util.function.BiConsumer;

public class StringArgument<U> extends Argument<String, U> {

    public StringArgument(Flag flag, BiConsumer<U, String> setter) {
        super(flag, 1, null, setter, setter);
    }

    public StringArgument(Flag flag, String defaultValue, BiConsumer<U, String> setter) {
        super(flag, 1, defaultValue, setter, setter);
    }
}
