package co.develhope.customqueries1.controllers;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatus;
import co.develhope.customqueries1.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getAll(){
        return flightRepository.findAll();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String generateRandomString(){
        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for(int i=0;i<targetStringLength;i++){
            buffer.append((char) getRandomNumber(97,122));
        }
        String returnedString= buffer.toString();
        return returnedString;
    }

    @PostMapping("/create")
    public List<Flight> provisionFlights(){
        Random random = new Random();
        List<Flight> flightList = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            Flight flight = new Flight();
            flight.setDescription(generateRandomString());
            flight.setFromAirport(generateRandomString());
            flight.setToAirport(generateRandomString());
            flight.setFlightStatus(FlightStatus.ONTIME);
            flightRepository.saveAndFlush(flight);
            flightList.add(flight);
        }
        return flightList;
    }
}