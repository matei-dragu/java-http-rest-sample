package com.sampleapp.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SimpleJUnit5Test {

    @Test
    void junit5TestShouldRunProperly() {
        log.info("This is a JUnit5 test");

        assertThat(1).isGreaterThan(0);
    }
}
