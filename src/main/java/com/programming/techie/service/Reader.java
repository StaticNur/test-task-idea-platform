package com.programming.techie.service;

import com.programming.techie.entity.Flight;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Reader {
    List<Flight> readAll(String fileName) throws IOException, ParseException;
}
