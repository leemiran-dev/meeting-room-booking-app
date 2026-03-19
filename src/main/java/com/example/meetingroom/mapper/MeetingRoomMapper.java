package com.example.meetingroom.mapper;

import com.example.meetingroom.domain.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingRoomMapper {
    List<MeetingRoom> findAll();
}
