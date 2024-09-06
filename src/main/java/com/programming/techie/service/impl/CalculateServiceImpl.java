package com.programming.techie.service.impl;

import com.programming.techie.entity.Flight;
import com.programming.techie.service.CalculateService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculateServiceImpl implements CalculateService {

    @Override
    public Map<String, Long> calculateMinFlightTimeByCarrier(List<Flight> flights) {
        return flights.stream()
                .collect(Collectors.groupingBy(
                        Flight::getCarrier,
                        Collectors.mapping(
                                Flight::getFlightDuration,
                                Collectors.minBy(Long::compare)
                        )
                )).entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().orElse(Long.MAX_VALUE)
                ));
    }

    @Override
    public List<Integer> getPrices(List<Flight> flights) {
        return flights.stream()
                .map(Flight::getPrice)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public double getAveragePrice(List<Integer> prices) {
        return prices.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    @Override
    public double getMedian(List<Integer> prices) {
        int size = prices.size();
        if (size % 2 == 1) {
            return prices.get(size / 2);
        } else {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        }
    }
}
