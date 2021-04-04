package com.grednine.testing;

import com.gridnine.testing.Main;
import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.SegmentService;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class CheckOnLandDurationTest {
    @Test
    public void testCheckOnLandDuration(){
        LocalDateTime ldt = LocalDateTime.now();

        Flight f1 = FlightService.createFlight(
                SegmentService.createSegment(ldt.plusHours(1),ldt.plusHours(3)));

        Flight f2 = FlightService.createFlight(
                SegmentService.createSegment(ldt.plusHours(1),ldt.plusHours(3)),
                SegmentService.createSegment(ldt.plusHours(6), ldt.plusHours(7)));

        Flight f3 = FlightService.createFlight(
                SegmentService.createSegment(ldt,ldt.plusHours(2)),
                SegmentService.createSegment(ldt.plusHours(3).plusMinutes(35), ldt.plusHours(7)),
                SegmentService.createSegment(ldt.plusHours(7).plusMinutes(35), ldt.plusHours(10)));

        Assert.assertTrue(Main.checkOnLandDuration(f1, Duration.ofMinutes(1)));

        Assert.assertFalse(Main.checkOnLandDuration(f2, Duration.ofHours(2)));
        Assert.assertTrue(Main.checkOnLandDuration(f2, Duration.ofHours(6)));

        Assert.assertFalse(Main.checkOnLandDuration(f3, Duration.ofHours(2)));
        Assert.assertTrue(Main.checkOnLandDuration(f3, Duration.ofHours(3)));
    }
}
