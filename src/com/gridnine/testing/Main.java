package com.gridnine.testing;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        List<Predicate<Flight>> predicates = new ArrayList<>();
        predicates.add(Main::checkFlyAfterNow);
        predicates.add(Main::checkBadSegments);
        final int ON_LAND_HOURS = 2;
        predicates.add((Flight f) ->checkOnLandDuration(f, Duration.ofHours(ON_LAND_HOURS)));

        //Применяем фильтры по одному
        flightFilters(flights, predicates.get(0));
        flightFilters(flights, predicates.get(1));
        flightFilters(flights, predicates.get(2));

        //Применим все фильтры сразу (вдруг их очень много)
        flightFilters(flights, predicates.toArray(new Predicate[]{}));

        //или, например, применим только первые два фильтра
        flightFilters(flights, predicates.get(0), predicates.get(1));
    }

    public static List<Flight> flightFilters(final List<Flight> flights, final Predicate<Flight>... predicates) {
        Stream<Flight> flightStream = flights.stream();
        for (Predicate<Flight> p: predicates) flightStream = flightStream.filter(p);
        List<Flight> finalFlights = flightStream.collect(Collectors.toList());
        finalFlights.forEach(System.out::println);
        System.out.println("-");
        return finalFlights;
    }

    public static boolean checkFlyAfterNow(final Flight flight) {
        return flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now());
    }
    public static boolean checkBadSegments(final Flight flight) {
        for (Segment s: flight.getSegments())
            if (s.getDepartureDate().isAfter(s.getArrivalDate())) return false;
        return true;
    }
    public static boolean checkOnLandDuration(final Flight flight, final Duration duration) {
        Duration dTemp = Duration.ZERO;
        for(int i=0; i < flight.getSegments().size()-1;i++) {
            dTemp= dTemp.plus(Duration.between(
                    flight.getSegments().get(i).getArrivalDate(),
                    flight.getSegments().get(i+1).getDepartureDate()));
            if (dTemp.compareTo(duration) == 1) return false;
        }
        return true;
    }
}

