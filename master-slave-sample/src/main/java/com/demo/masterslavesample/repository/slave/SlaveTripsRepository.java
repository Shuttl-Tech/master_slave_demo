package com.demo.masterslavesample.repository.slave;

import com.demo.masterslavesample.model.TripsModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Qualifier("slaveTripsRepository")
public interface SlaveTripsRepository extends JpaRepository<TripsModel, String> {

    @Transactional
    @Query(value = "SELECT t FROM TripsModel t" +
            " WHERE t.city = :city" +
            " AND t.tripId IN (:tripIds)")
    List<TripsModel> findTripsByFilters(@Param("city") String city,
                                        @Param("tripIds") List<String> tripIds);
}