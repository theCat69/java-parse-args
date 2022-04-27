package com.wha.parseargs.argument;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class Flag {
    private final String value;
    private final String valueMatch;
    private Optional<String> alias;
    private String aliasMatch;

    public Flag(String value) {
        this.value = value;
        this.valueMatch = "-" + value;
        this.alias = Optional.empty();
    }

    public Flag(String value, String alias) {
        this.value = value;
        this.valueMatch = "-" + value;
        this.alias = Optional.of(alias);
        this.aliasMatch = "--" + alias;
    }

    public Optional<Integer> getIndexOfMatchInList(List<String> list) {
        int index = list.indexOf(valueMatch);
        if(index < 0 && alias.isPresent()) {
            index = list.indexOf(aliasMatch);
        }
        return index != -1 ? Optional.of(index) : Optional.empty();
    }
}
