package com.example.meetingroom.controller;

import com.example.meetingroom.domain.MeetingRoom;
import com.example.meetingroom.mapper.MeetingRoomMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8081")

public class MeetingRoomController {
    private final MeetingRoomMapper meetingRoomMapper;

    public MeetingRoomController(MeetingRoomMapper meetingRoomMapper) {
        this.meetingRoomMapper = meetingRoomMapper;
    }

    @GetMapping("/api/rooms")
    public List<MeetingRoom> getRooms() {
        return meetingRoomMapper.findAll();
    }
}
