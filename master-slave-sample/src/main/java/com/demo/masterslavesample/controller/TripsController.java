package com.demo.masterslavesample.controller;

import com.demo.masterslavesample.model.TripsModel;
import com.demo.masterslavesample.service.TripImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TripsController {

    private final TripImpl trip;

    @Autowired
    public TripsController(TripImpl trip) {
        this.trip = trip;
    }

    @PostMapping("internal/v1/trips/master-test/{tripId}")
    public ResponseEntity<String> testMaster(
            @PathVariable String tripId
    ) {

        trip.updateActiveTripStatus(tripId);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/internal/v1/trips/slave-test/{tripId}")
    public  List<TripsModel> testSlave(
            @PathVariable String tripId
            ) {
        List<String> trips = new ArrayList<>();
        trips.add(tripId);

        return trip.findTripsByFilters(trips);
    }

    @PatchMapping("/internal/v1/trips/jdbc-test")
    public void testSlave(
    ) {
        trip.bulkUpdateMaxVehicleCapacity();
    }

    @GetMapping("/internal/v1/trips/slave-test/find/{tripId}")
    public Optional<TripsModel> testSlaveTrip(
            @PathVariable String tripId
    ) {

        return trip.findById(tripId);
    }
}
