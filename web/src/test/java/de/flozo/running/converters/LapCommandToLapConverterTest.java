package de.flozo.running.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LapCommandToLapConverterTest {

    LapCommandToLapConverter lapCommandToLapConverter;

    @BeforeEach
    void setUp() {
        lapCommandToLapConverter = new LapCommandToLapConverter();
    }

    @Test
    void timeToMilliseconds() {
        LocalTime time = LocalTime.of(12, 34, 56, 789000000);
        Long milliseconds = lapCommandToLapConverter.timeToMilliseconds(time);
        assertEquals(45296789L, milliseconds);
    }

    @Test
    void convert() {
    }
}
