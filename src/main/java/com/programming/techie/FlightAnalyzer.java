package com.programming.techie;

import com.programming.techie.entity.Flight;
import com.programming.techie.service.CalculateService;
import com.programming.techie.service.impl.CalculateServiceImpl;
import com.programming.techie.service.Reader;
import com.programming.techie.service.impl.ReaderJson;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class FlightAnalyzer {
    private final Reader reader;
    private final CalculateService calculateService;

    public FlightAnalyzer(Reader reader, CalculateServiceImpl calculateServiceImpl) {
        this.reader = reader;
        this.calculateService = calculateServiceImpl;
    }

    public static void main(String[] args) throws IOException, ParseException {
        FlightAnalyzer analyzer = new FlightAnalyzer(new ReaderJson(), new CalculateServiceImpl());

        List<Flight> flights = analyzer.reader.readAll("tickets.json");

        Map<String, Long> minFlightTimes = analyzer.calculateService.calculateMinFlightTimeByCarrier(flights);

        System.out.println("Minimum flight times (in minutes) for each carrier:");
        minFlightTimes.forEach((carrier, time) ->
                System.out.println(carrier + ": " + time + " minutes"));

        List<Integer> prices = analyzer.calculateService.getPrices(flights);
        double averagePrice = analyzer.calculateService.getAveragePrice(prices);
        double medianPrice = analyzer.calculateService.getMedian(prices);

        System.out.println("\nAverage price: " + averagePrice);
        System.out.println("Median price: " + medianPrice);
        System.out.println("Difference between average and median: " + (averagePrice - medianPrice));
    }
}

