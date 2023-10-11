package com.demo.masterslavesample.service;

import com.demo.masterslavesample.model.TripsModel;
import com.demo.masterslavesample.repository.TripsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TripImpl {

    private final TripsRepository tripsRepository;

    @Autowired
    public TripImpl(TripsRepository tripsRepository) {
        this.tripsRepository = tripsRepository;
    }

    public void  updateActiveTripStatus(String tripId){
        tripsRepository.updateActiveTripStatus(tripId, 750000L);
    }

    public List<TripsModel> findTripsByFilters(List<String> trips){
        return tripsRepository.findTripsByFilters("City1", trips);
    }

    public Optional<TripsModel> findById(String tripId){
        return tripsRepository.findById(tripId);
    }

    public void bulkUpdateMaxVehicleCapacity() {
        Map<String, Integer> tripCapacityMap = new HashMap<>();
        tripCapacityMap.put("1", 20);
        tripCapacityMap.put("2", 25);

        tripsRepository.bulkUpdateMaxVehicleCapacity(tripCapacityMap);

    }
}
