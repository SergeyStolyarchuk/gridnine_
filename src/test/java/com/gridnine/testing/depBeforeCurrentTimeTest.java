package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.FilterListFlight.filterFlight;

class depBeforeCurrentTimeTest {

    private LocalDateTime threeDaysFromNowTest;
    private List<Flight> flightTest, flightTestActual;

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }

    @BeforeEach

    void setUp() {
        threeDaysFromNowTest = LocalDateTime.now().plusDays(3);
        flightTest = Arrays.asList(
                createFlight(threeDaysFromNowTest, threeDaysFromNowTest.plusHours(2), threeDaysFromNowTest.minusDays(5), threeDaysFromNowTest.plusHours(2)),
                createFlight(threeDaysFromNowTest.minusDays(4), threeDaysFromNowTest.plusHours(2)),
                createFlight(threeDaysFromNowTest, threeDaysFromNowTest.plusHours(2)));
        flightTestActual = Arrays.asList(
                createFlight(threeDaysFromNowTest, threeDaysFromNowTest.plusHours(2), threeDaysFromNowTest.minusDays(5), threeDaysFromNowTest.plusHours(2)),
                createFlight(threeDaysFromNowTest.minusDays(4), threeDaysFromNowTest.plusHours(2)));

    }


    @Test
    void test2StringEquals() {
        Assertions.assertEquals(filterFlight(flightTest, new depBeforeCurrentTime()).toString(), flightTestActual.toString());
    }
}