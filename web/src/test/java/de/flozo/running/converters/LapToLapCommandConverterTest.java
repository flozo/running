package de.flozo.running.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LapToLapCommandConverterTest {

    LapToLapCommandConverter lapToLapCommandConverter;

    @BeforeEach
    void setUp() {
        lapToLapCommandConverter = new LapToLapCommandConverter();
    }

    @Test
    void longToTime() {
        LocalTime expectedTime = LocalTime.of(12, 34, 56, 789000000);
        Long milliseconds = 45296789L;
        LocalTime time = lapToLapCommandConverter.longToTime(milliseconds);
        assertEquals(expectedTime, time);
    }

    @Test
    void convert() {
    }
}
