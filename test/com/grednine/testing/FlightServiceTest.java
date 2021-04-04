package com.grednine.testing;

import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.SegmentService;
import com.gridnine.testing.service.exceptions.FlightConstrainException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightServiceTest {
    @Test
    public void testFalseCreateFlight(){
        LocalDateTime ldt = LocalDateTime.now();
        Segment s1 = SegmentService.createSegment(ldt, ldt.plusHours(3));
        Segment s2 = SegmentService.createSegment(ldt.plusHours(2), ldt.plusHours(5));

        FlightConstrainException thrown = assertThrows(FlightConstrainException.class, ()->
                FlightService.createFlight(s1,s2));
        assertNotNull(thrown);
    }

    @Test
    public void testOkCreateFlight(){
        LocalDateTime ldt = LocalDateTime.now();
        Segment s1 = SegmentService.createSegment(ldt, ldt.plusHours(1));
        Segment s2 = SegmentService.createSegment(ldt.plusHours(2), ldt.plusHours(5));
        Segment s3 = SegmentService.createSegment(ldt.plusHours(7), ldt.plusHours(8));

        Assert.assertEquals(FlightService.createFlight(s1,s2,s3).getSegments().size(), 3);
    }

    @Test
    public void testLengthNullSegments(){
        FlightConstrainException thrown = assertThrows(FlightConstrainException.class, ()->
                FlightService.createFlight());
        assertNotNull(thrown);
    }
}
