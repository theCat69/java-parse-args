package com.wha.parseargs;

import com.wha.parseargs.argument.Argument;
import com.wha.parseargs.argument.Flag;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * TODO add strict
 *
 * @param <T>
 */
@RequiredArgsConstructor
public class ArgParser<T> {

    private final Schema<T> schema;

    public T parseArgs(String[] args, Supplier<T> dtoSupplier) {
        return parseArgs(args, dtoSupplier.get());
    }

    public T parseArgs(String[] args, T dto) {
        List<String> argsList = Arrays.asList(args);
        schema.getArguments().forEach(arg -> setValue(dto, arg, argsList));
        return dto;
    }

    private void setValue(T dto, Argument<?, T> argument, List<String> argsList) {
        argument.getFlag().getIndexOfMatchInList(argsList).ifPresentOrElse(
                index -> {
                    if (argument.getArgNb() == 0) {
                        argument.getSetValueToTarget().accept(dto, null);
                    } else if (argument.getArgNb() >= 1 && argsList.size() == index + 1) {
                        throw new RuntimeException("argument expected");
                    } else {
                        argument.getSetValueToTarget().accept(dto, argsList.get(index + 1));
                    }
                },
                () -> argument.setDefaultValueToTarget(dto)
        );
    }
}
