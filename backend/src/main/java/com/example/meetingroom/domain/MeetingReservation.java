package com.example.meetingroom.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeetingReservation {
    private Integer reservationId;
    private Integer roomId;
    private String reserverName;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId){
        this.reservationId = reservationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getReserverName() {
        return reserverName;
    }

    public void setReserverName(String reserverName) {
        this.reserverName = reserverName;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
