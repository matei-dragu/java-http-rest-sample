package com.sampleapp.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SimpleJUnit4Test {

    @Test
    public void junit4TestShouldRunProperly() {
        log.info("This is a JUnit4 test which will be run by the new JUnit Vintage runner");
        StackWalker.getInstance().forEach(stackFrame -> {
            log.info("Frame: {}", stackFrame);
        });

        assertThat(1).isGreaterThan(0);
    }
}
