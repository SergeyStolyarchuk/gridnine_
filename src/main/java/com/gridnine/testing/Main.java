package com.gridnine.testing;

import java.util.List;

import static com.gridnine.testing.FilterListFlight.filterFlight;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();
        System.out.println("1. вылет до текущего момента времени");
        System.out.println(filterFlight(flightList, new depBeforeCurrentTime()));
        System.out.println();
        System.out.println("2. имеются сегменты с датой прилёта раньше даты вылета");
        System.out.println(filterFlight(flightList, new dateArrEarlierDep()));
        System.out.println();
        System.out.println("3. общее время, проведённое на земле превышает два часа ");
        System.out.println(filterFlight(flightList, new onEarthMoreThan2H()));
    }
}