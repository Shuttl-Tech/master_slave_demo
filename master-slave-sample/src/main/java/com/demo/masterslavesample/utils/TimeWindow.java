package com.demo.masterslavesample.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimeWindow {
    private Long fromTime;
    private Long toTime;

    public TimeWindow(Long fromTime, Long toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public long getFromTime() {
        return fromTime;
    }

    public long getToTime() {
        return toTime;
    }

    public static TimeWindow getTimeWindowOfIstDate(LocalDate date) {
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        Long fromTime=date.atTime(LocalTime.MIN).atZone(istZone).toEpochSecond();
        Long toTime=date.atTime(LocalTime.MAX).atZone(istZone).toEpochSecond();
        return new TimeWindow(fromTime*1000L, toTime*1000L);
    }

}

