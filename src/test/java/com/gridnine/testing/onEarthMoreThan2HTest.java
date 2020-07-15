package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.FilterListFlight.filterFlight;



    class onEarthMoreThan2HTest {

        private LocalDateTime now;
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
            now = LocalDateTime.now();
            flightTest = Arrays.asList(
                    createFlight(now, now.plusHours(2), now.plusHours(5), now.plusHours(7)),
                    createFlight(now.plusHours(5), now.plusHours(3), now, now),
                    createFlight(now, now, now.plusDays(3), now),
                    createFlight(now, now),
                    createFlight(now, now, now, now, now, now, now, now));
            flightTestActual = Arrays.asList(
                    createFlight(now, now.plusHours(2), now.plusHours(5), now.plusHours(7)),
                    createFlight(now, now, now.plusDays(3), now));

        }


        @Test
        void test2StringEquals() {
            Assertions.assertEquals(filterFlight(flightTest, new onEarthMoreThan2H()).toString(), flightTestActual.toString());
        }
    }
