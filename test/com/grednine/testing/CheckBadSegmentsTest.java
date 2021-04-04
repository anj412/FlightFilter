package com.grednine.testing;

import com.gridnine.testing.Main;
import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.SegmentService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CheckBadSegmentsTest {
    @Test
    public void testCheckFindBadSegment(){
        LocalDateTime ldt = LocalDateTime.now();

        Flight f = FlightService.createFlight(
                new Segment(ldt, ldt.minusMinutes(5)),
                SegmentService.createSegment(ldt.plusHours(1),ldt.plusHours(3)));
        Flight f1 = FlightService.createFlight(
                SegmentService.createSegment(ldt.plusHours(1),ldt.plusHours(3)),
                SegmentService.createSegment(ldt.plusHours(5), ldt.plusHours(7)));

        Assert.assertFalse(Main.checkBadSegments(f));
        Assert.assertTrue(Main.checkBadSegments(f1));

    }
}
