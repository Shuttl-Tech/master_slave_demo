package com.demo.masterslavesample.repository;

import com.demo.masterslavesample.model.TripsModel;
import com.demo.masterslavesample.repository.master.MasterTripsRepository;
import com.demo.masterslavesample.repository.slave.SlaveTripsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TripsRepository {

    @Qualifier("masterTripsRepository")
    private MasterTripsRepository masterRepo;

    @Qualifier("slaveTripsRepository")
    private SlaveTripsRepository slaveRepo;

    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    public TripsRepository(MasterTripsRepository masterRepo, SlaveTripsRepository slaveRepo, JdbcTemplate masterJdbcTemplate) {
        this.masterRepo = masterRepo;
        this.slaveRepo = slaveRepo;
        this.masterJdbcTemplate = masterJdbcTemplate;
    }

    public void updateActiveTripStatus(String tripId, Long actualTripStartTime) {
        masterRepo.updateActiveTripStatus(tripId, actualTripStartTime);
    }

    public List<TripsModel> findTripsByFilters(String city, List<String> tripIds) {
        return slaveRepo.findTripsByFilters(city, tripIds);
    }

    public void bulkUpdateMaxVehicleCapacity(Map<String, Integer> tripCapacityMap) {
        String updateQuery = "UPDATE trips SET max_vehicle_capacity = ? WHERE trip_id = ?";

        List<Object[]> batchArgs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : tripCapacityMap.entrySet()) {
            Object[] args = {entry.getValue(), entry.getKey()};
            batchArgs.add(args);
        }

        JdbcTemplate jdbcTemplateToUse = masterJdbcTemplate;

        jdbcTemplateToUse.batchUpdate(updateQuery, batchArgs);
    }

    public Optional<TripsModel> findById(String tripId) {
        return slaveRepo.findById(tripId);
    }
}
