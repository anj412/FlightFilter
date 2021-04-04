package com.gridnine.testing.service;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.service.exceptions.FlightConstrainException;

import java.util.Arrays;
import java.util.Collections;

public class FlightService {
    public static Flight createFlight (final Segment... segments) {
        if (segments.length == 0) throw new FlightConstrainException("Number segments must be not 0");
        for (int i=0; i < segments.length-1; i++)
            if (!segments[i].getArrivalDate().isBefore(segments[i+1].getDepartureDate()))
                throw new FlightConstrainException();
        return new Flight(Arrays.asList(segments));
    }

}
