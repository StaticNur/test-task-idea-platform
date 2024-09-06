package com.programming.techie.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programming.techie.entity.Flight;
import com.programming.techie.service.Reader;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReaderJson implements Reader {
    private static final String ORIGIN_CITY = "VVO";
    private static final String DESTINATION_CITY = "TLV";
    private static final String FORMAT_DATE = "dd.MM.yy HH:mm";

    @Override
    public List<Flight> readAll(String nameFile) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(nameFile));

        List<Flight> flights = new ArrayList<>();
        for (JsonNode ticket : rootNode.get("tickets")) {
            Optional<Flight> read = read(ticket);
            read.ifPresent(flights::add);
        }
        return flights;
    }

    public Optional<Flight> read(JsonNode ticket) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);

        String origin = ticket.get("origin").asText();
        String destination = ticket.get("destination").asText();
        if (!ORIGIN_CITY.equals(origin) || !DESTINATION_CITY.equals(destination)) {
            return Optional.empty();
        }
        String carrier = ticket.get("carrier").asText();
        Date departure = dateFormat.parse(ticket.get("departure_date").asText()
                                          + " " + ticket.get("departure_time").asText());
        Date arrival = dateFormat.parse(ticket.get("arrival_date").asText()
                                        + " " + ticket.get("arrival_time").asText());
        int price = ticket.get("price").asInt();

        return Optional.of(new Flight(carrier, departure, arrival, price));
    }
}
