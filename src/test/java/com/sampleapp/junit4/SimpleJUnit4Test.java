package com.sampleapp.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class SimpleJUnit4Test {

    @Test
    void junit4TestShouldRunProperly() {
        log.info("This is a JUnit4 test");

        assertThat(1).isGreaterThan(0);
    }
}
