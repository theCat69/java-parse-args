package com.wha.parseargs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@NoArgsConstructor
@Getter
@Setter
public class ExampleDto {
    private String directory;
    private Integer port;
    private Boolean logging;

    private Path pathDirectory;
}
