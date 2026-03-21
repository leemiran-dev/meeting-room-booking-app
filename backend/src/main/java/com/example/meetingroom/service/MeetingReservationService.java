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
        // 1. 시작 시간이 종료 시간보다 빠르지 않다면...?
        if(!reservation.getStartTime().isBefore(reservation.getEndTime())){
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다!");
        }
        // 2. 같은 회의실, 같은 날짜에 겹치는 예약이 있는 지 확인
        int conflictCount = meetingReservationMapper.countConflict(
                reservation.getRoomId(),
                reservation.getReservationDate(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
        if(conflictCount > 0) {
            throw new IllegalArgumentException("이미 예약된 시간입니다.");
        }
        // 3. 이상 없으면,
        meetingReservationMapper.insertReservation(reservation);
    }

    public List<MeetingReservation> getReservationByDate(LocalDate reservationDate) {
        return meetingReservationMapper.findByDate(reservationDate);
    }

    public void deleteReservation(Integer reservationId){
        int deletedCount = meetingReservationMapper.deleteReservation(reservationId);

        if(deletedCount == 0){
            throw new IllegalArgumentException("해당 예약이 존재하지 않습니다.");
        }
    }
}
