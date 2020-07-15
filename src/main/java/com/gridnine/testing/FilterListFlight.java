package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class FilterListFlight {

    public static List<Flight> filterFlight (List<Flight> flights, Searchable s) {

        return flights.stream()
                .filter(f -> s.test(f))
                .collect(Collectors.toList());
    }

}
