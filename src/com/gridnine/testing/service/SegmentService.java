package com.gridnine.testing.service;

import com.gridnine.testing.service.exceptions.SegmentConstrainException;

import com.gridnine.testing.domain.Segment;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

final public class SegmentService {
    public static Segment createSegment(final LocalDateTime departureDate, final LocalDateTime arrivalDate) {
        if (Objects.isNull(departureDate) || Objects.isNull(arrivalDate)) throw new SegmentConstrainException("Dates must be not null");
        if (!departureDate.isBefore(arrivalDate)) throw new SegmentConstrainException("" +
                "DepartureDate must be early then arrivalDate");
        return new Segment(departureDate, arrivalDate);
    }
}
