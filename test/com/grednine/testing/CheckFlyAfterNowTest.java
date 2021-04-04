package com.grednine.testing;

import com.gridnine.testing.Main;
import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.SegmentService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CheckFlyAfterNowTest {
    @Test
    public void testFind(){
        LocalDateTime ldt = LocalDateTime.now();

        Flight f1 = FlightService.createFlight(
                SegmentService.createSegment(ldt.plusHours(1),ldt.plusHours(3)));

        Flight f2 = FlightService.createFlight(
                SegmentService.createSegment(ldt.minusHours(1),ldt.plusHours(3)),
                SegmentService.createSegment(ldt.plusHours(6), ldt.plusHours(7)));

        Assert.assertTrue(Main.checkFlyAfterNow(f1));
        Assert.assertFalse(Main.checkFlyAfterNow(f2));
    }
}
