package com.sampleapp.rest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
class TravisCiTest {

    @Test
    void failingTest() {
        log.info("This test should fail");

        fail("This is a failing test");
    }
}
