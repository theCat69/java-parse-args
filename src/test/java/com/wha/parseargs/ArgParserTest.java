package com.wha.parseargs;

import com.wha.parseargs.argument.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArgParserTest {

    public static final String DIR_DEFAULT_VALUE = ".";
    public static final int PORT_DEFAULT_VALUE = 8080;
    public static final boolean LOGGING_DEFAULT_VALUE = false;

    private final ArgParser<ExampleDto> argParser = new ArgParser<>(new Schema<>(List.of(
            new BooleanArgument<>(new Flag("l"), LOGGING_DEFAULT_VALUE, ExampleDto::setLogging),
            new StringArgument<>(new Flag("d"), DIR_DEFAULT_VALUE, ExampleDto::setDirectory),
            new IntegerArgument<>(new Flag("p"), PORT_DEFAULT_VALUE, ExampleDto::setPort)
    )));

    @Test
    void logginAndPortTest() {
        String commandLine = "-l -p 9090";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), ExampleDto::new);

        assertThat(exampleDto).isNotNull()
                .returns(true, ExampleDto::getLogging)
                .returns(9090, ExampleDto::getPort)
                .returns(DIR_DEFAULT_VALUE, ExampleDto::getDirectory);
    }

    @Test
    void booleanLast() {
        String commandLine = "-d /c/kafka -p 9090 -l";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(true, ExampleDto::getLogging)
                .returns(9090, ExampleDto::getPort)
                .returns("/c/kafka", ExampleDto::getDirectory);
    }

    @Test
    void emptyArgs() {
        String commandLine = "";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(LOGGING_DEFAULT_VALUE, ExampleDto::getLogging)
                .returns(PORT_DEFAULT_VALUE, ExampleDto::getPort)
                .returns(DIR_DEFAULT_VALUE, ExampleDto::getDirectory);
    }

    @Test
    void negativePort() {
        String commandLine = "-p -7909";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(-7909, ExampleDto::getPort);
    }

    @Test
    void uknownParametersAreIgnored() {
        String commandLine = "-p -7909 -x -q okdza iiid -77 -F";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(-7909, ExampleDto::getPort)
                .returns(LOGGING_DEFAULT_VALUE, ExampleDto::getLogging)
                .returns(DIR_DEFAULT_VALUE, ExampleDto::getDirectory);
    }

    @Test
    void nullDefaultValues() {
        ArgParser<ExampleDto> argParser = new ArgParser<>(new Schema<>(
                List.of(
                        new BooleanArgument<>(new Flag("l"), null, ExampleDto::setLogging),
                        new StringArgument<>(new Flag("d"), null, ExampleDto::setDirectory),
                        new IntegerArgument<>(new Flag("p"), null, ExampleDto::setPort)
                )
        ));

        String commandLine = "";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(null, ExampleDto::getPort)
                .returns(null, ExampleDto::getLogging)
                .returns(null, ExampleDto::getDirectory);
    }

    @Test
    void unhandledTypeExample() {
        ArgParser<ExampleDto> argParser = new ArgParser<>(new Schema<>(
                List.of(
                        new Argument<>(
                                new Flag("d"),
                                1,
                                Path.of("."),
                                ExampleDto::setPathDirectory,
                                (exampleDto, s) -> exampleDto.setPathDirectory(Path.of(s)))
                )
        ));

        String commandLine = "-d /c/kafka";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(Path.of("/c/kafka"), ExampleDto::getPathDirectory);
    }

    @Test
    void unhandledTypeExampleDefaultValue() {
        ArgParser<ExampleDto> argParser = new ArgParser<>(new Schema<>(
                List.of(
                        new Argument<>(
                                new Flag("d"),
                                1,
                                Path.of("."),
                                ExampleDto::setPathDirectory,
                                (exampleDto, s) -> exampleDto.setPathDirectory(Path.of(s)))
                )
        ));

        String commandLine = "";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns(Path.of("."), ExampleDto::getPathDirectory);
    }

    @Test
    void aliasFlag() {
        ArgParser<ExampleDto> argParser = new ArgParser<>(new Schema<>(
                List.of(
                        new StringArgument<>(new Flag("d", "directory"), null, ExampleDto::setDirectory)
                )
        ));

        String commandLine = "--directory /c/kafka";

        ExampleDto exampleDto = argParser.parseArgs(commandLine.split(" "), new ExampleDto());

        assertThat(exampleDto).isNotNull()
                .returns("/c/kafka", ExampleDto::getDirectory);
    }

}