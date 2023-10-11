package com.demo.masterslavesample.repository.master;


import com.demo.masterslavesample.model.TripsModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Qualifier("masterTripsRepository")
public interface MasterTripsRepository extends JpaRepository<TripsModel, String>  {

    @Modifying
    @Transactional
    @Query(value = "UPDATE trips SET trip_status = 'ACTIVE', actual_trip_start_time = :actualTripStartTime, updated_by = 'ETIM', last_updated_at =CURRENT_TIMESTAMP WHERE trip_id = :tripId", nativeQuery = true)
    void updateActiveTripStatus(@Param("tripId") String tripId, @Param("actualTripStartTime") Long actualTripStartTime);

}