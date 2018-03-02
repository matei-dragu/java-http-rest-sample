package com.sampleapp.vavr.values;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
class ValuesTest {

    @Test
    void optionValueTest() {
        Option<String> stringOption = Option.of("SomeString");

        then(stringOption.get()).isEqualTo("SomeString");
        then(stringOption).isEqualTo(Option.some("SomeString"));

        Option<String> mappedToNull = stringOption.map(s -> (String) null);
        log.info("stringOption: {} => mappedToNull: {}", stringOption, mappedToNull);

        then(mappedToNull).isEqualTo(Option.some(null));
    }

    @Test
    void optionOfNullValueTest() {
        Option<String> optionOfNull = Option.of(null);
        log.info("optionOfNull: {}", optionOfNull);

        Option<String> mappedOptionOfNull = optionOfNull.map(s -> s + "bar");
        log.info("mappedOptionOfNull: {}", mappedOptionOfNull);

        Option<String> maybeString = optionOfNull.flatMap(s -> Option.of(s).map(innerS -> innerS + "baz"));
        log.info("maybeString: {}", maybeString);

        then(optionOfNull).isEqualTo(Option.none());
        then(mappedOptionOfNull).isEqualTo(Option.none());
        then(maybeString).isEqualTo(Option.none());

    }
}
