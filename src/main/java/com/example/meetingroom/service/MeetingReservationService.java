package com.example.meetingroom.service;

import com.example.meetingroom.domain.MeetingReservation;
import com.example.meetingroom.mapper.MeetingReservationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MeetingReservationService {
    private final MeetingReservationMapper meetingReservationMapper;

    public MeetingReservationService(MeetingReservationMapper meetingReservationMapper) {
        this.meetingReservationMapper = meetingReservationMapper;
    }

    public void createReservation(MeetingReservation reservation) {
        meetingReservationMapper.insertReservation(reservation);
    }

    public List<MeetingReservation> getReservationByDate(LocalDate reservationDate) {
        return meetingReservationMapper.findByDate(reservationDate);
    }
}
