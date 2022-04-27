package com.wha.parseargs;

import com.wha.parseargs.argument.Argument;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class Schema<U> {
    private final List<Argument<?, U>> arguments;
}
