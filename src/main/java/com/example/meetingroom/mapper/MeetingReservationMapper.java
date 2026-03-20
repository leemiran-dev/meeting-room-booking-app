package com.example.meetingroom.mapper;

import com.example.meetingroom.domain.MeetingReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface MeetingReservationMapper {
    int insertReservation(MeetingReservation reservation);

    // 날짜로 예약 조회
    List<MeetingReservation> findByDate(@Param("reservationDate") LocalDate reservationDate);

    // 예약 검증 조회
    int countConflict(
            @Param("roomId") Integer roomId,
            @Param("reservationDate") LocalDate reservationDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
            );
}
