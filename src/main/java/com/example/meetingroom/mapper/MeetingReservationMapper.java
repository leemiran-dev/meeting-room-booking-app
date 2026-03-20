package com.example.meetingroom.mapper;

import com.example.meetingroom.domain.MeetingReservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingReservationMapper {
    int insertReservation(MeetingReservation reservation);

}
