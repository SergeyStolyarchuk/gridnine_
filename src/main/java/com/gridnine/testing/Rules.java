package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

// 1. вылет до текущего момента времени
class depBeforeCurrentTime implements Searchable {

    private boolean flag = false;

    public boolean test(Flight flight) {

        for (Segment s: flight.getSegments()) {
            flag = s.getDepartureDate().isBefore(LocalDateTime.now());
        }
        return  flag;
    }

}

//2. имеются сегменты с датой прилёта раньше даты вылета .
class dateArrEarlierDep implements Searchable {

    public boolean test(Flight flight) {

    for (Segment segment: flight.getSegments()) {
        if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
            return true;
        }
    }
    return false;

}

}
    /* 3.	общее время, проведённое на земле превышает два часа
    (время на земле — это интервал между прилётом одного сегмента
    и вылетом следующего за ним) */

    class onEarthMoreThan2H implements Searchable {

    private List<Segment> segment;

    public boolean test(Flight flight) {
    Duration d1 = Duration.ofHours(0);
    Duration d2 = Duration.ofHours(2);
    segment = flight.getSegments();
    if (segment.size() >= 2) {
        for (int i = 0; i < segment.size() - 1; i++) {
            d1 = d1.plus(Duration.between(segment.get(i).getArrivalDate(), segment.get(i + 1).getDepartureDate()));
        }
        if (d1.compareTo(d2) > 0) {
            return true;
        }
    }

    return false;
}

}