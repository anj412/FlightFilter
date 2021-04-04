package com.gridnine.testing.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Bean that represents a flight segment.
 */
public class Segment {
    final private LocalDateTime departureDate;

    final private LocalDateTime arrivalDate;

    public Segment(final LocalDateTime dep, final LocalDateTime arr) {
        departureDate = dep;
        arrivalDate = arr;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}
