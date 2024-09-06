package com.programming.techie.service;

import com.programming.techie.entity.Flight;

import java.util.List;
import java.util.Map;

public interface CalculateService {
    Map<String, Long> calculateMinFlightTimeByCarrier(List<Flight> flights);

    List<Integer> getPrices(List<Flight> flights);

    double getAveragePrice(List<Integer> prices);

    double getMedian(List<Integer> prices);
}
