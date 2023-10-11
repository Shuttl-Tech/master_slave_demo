package com.demo.masterslavesample.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trips")
public class TripsModel {
    @Id
    @Column(name = "trip_id", columnDefinition = "VARCHAR(10)")
    private String tripId;

    @Column(name = "route_id", columnDefinition = "VARCHAR(10)")
    private String routeId;

    @Column(name = "city", columnDefinition = "VARCHAR(40)")
    private String city;

    @Column(name = "trip_scheduled_start", columnDefinition = "bigint")
    private Long tripScheduledStart;

    @Column(name = "updated_by", columnDefinition = "VARCHAR(50)")
    private String updatedBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

    @Column(name = "actual_trip_start_time", columnDefinition = "bigint")
    private Long actualTripStartTime;

    @Column(name = "actual_trip_end_time", columnDefinition = "bigint")
    private Long actualTripEndTime;

    @Column(name = "trip_type", columnDefinition = "INTEGER")
    private Integer tripType;

    @Column(name = "max_vehicle_capacity", columnDefinition = "INTEGER")
    private Integer maxVehicleCapacity;

    @Column(name = "pre_booking_window_days", columnDefinition = "INTEGER")
    private Integer preBookingWindowDays;

    @Column(name = "pre_booking_window_start_time", columnDefinition = "bigint")
    private Long preBookingWindowStartTime;

    @Column(name = "pre_booking_window_closure", columnDefinition = "INTEGER")
    private Integer preBookingWindowClosure;

    @Column(name = "pre_booking_window_end_time", columnDefinition = "bigint")
    private Long preBookingWindowEndTime;

    @Column(name = "trip_status", columnDefinition = "VARCHAR(20)")
    private String tripStatus;

    @Column(name = "upload_id", columnDefinition = "VARCHAR(50)")
    private String uploadId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Long> eta;

    @Column(name = "cancellation_reason", columnDefinition = "VARCHAR(255)")
    private String cancellationReason;


    @Override
    public String toString() {
        return "TripsModel{" +
                "tripId='" + tripId + '\'' +
                ", routeId='" + routeId + '\'' +
                ", city='" + city + '\'' +
                ", tripScheduledStart=" + tripScheduledStart +
                ", updatedBy='" + updatedBy + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", actualTripStartTime=" + actualTripStartTime +
                ", actualTripEndTime=" + actualTripEndTime +
                ", tripType=" + tripType +
                ", maxVehicleCapacity=" + maxVehicleCapacity +
                ", preBookingWindowDays=" + preBookingWindowDays +
                ", preBookingWindowStartTime=" + preBookingWindowStartTime +
                ", preBookingWindowClosure=" + preBookingWindowClosure +
                ", preBookingWindowEndTime=" + preBookingWindowEndTime +
                ", tripStatus='" + tripStatus + '\'' +
                ", uploadId='" + uploadId + '\'' +
                ", eta=" + eta +
                ", cancellationReason='" + cancellationReason + '\'' +
                '}';
    }

    public Optional<Long> getEtaOfStopId(String stopId) {
        if (eta != null && eta.containsKey(stopId)) {
            return Optional.ofNullable(eta.get(stopId));
        }
        return null;
    }
}
