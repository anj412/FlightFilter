package com.grednine.testing;

import com.gridnine.testing.Main;
import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.SegmentService;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FlightFiltersTest {
    @Test
    public void testFlightFilters(){
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

        Flight f4 = FlightService.createFlight(
                SegmentService.createSegment(ldt.minusMinutes(5),ldt.plusHours(2)));

        List<Flight> flights = Arrays.asList(f1, f2, f3, f4);

        int count1 = Main.flightFilters(flights,
                Main::checkFlyAfterNow,
                (Flight f) ->Main.checkOnLandDuration(f, Duration.ofHours(2))).size();
        int count2 = Main.flightFilters(flights, Main::checkFlyAfterNow).size();
        int count3 = Main.flightFilters(flights, (Flight f) ->Main.checkOnLandDuration(f, Duration.ofHours(2))).size();

        Assert.assertEquals(count1, 1);
        Assert.assertEquals(count2, 2);
        Assert.assertEquals(count3, 2);
    }
}
