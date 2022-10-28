package org.example;

import java.time.LocalDateTime;

public class PeriodTime implements Comparable<PeriodTime> {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PeriodTime() {
    }

    public PeriodTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "PeriodTime{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public int compareTo(PeriodTime periodTime) {
        if (this.startTime.isEqual(periodTime.getStartTime()))
            return this.endTime.compareTo(periodTime.getEndTime());
        else
            return this.startTime.compareTo(periodTime.getStartTime());
    }

//    @Override
//    public boolean equals(Object obj) {
//        return !super.equals(obj);
//    }
//
//    @Override
//    public int hashCode() {
//        return this.startTime.hashCode() * this.endTime.hashCode();
//    }
}
