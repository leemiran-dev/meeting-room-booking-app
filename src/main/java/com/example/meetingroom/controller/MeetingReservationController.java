package com.example.meetingroom.controller;

import com.example.meetingroom.service.MeetingReservationService;
import com.example.meetingroom.domain.MeetingReservation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins="http://localhost:8081")
public class MeetingReservationController {
    private final MeetingReservationService meetingReservationService;

    public MeetingReservationController(MeetingReservationService meetingReservationService){
        this.meetingReservationService = meetingReservationService;
    }

    @PostMapping
    public Map<String, String> createReservation(@RequestBody MeetingReservation reservation){
        meetingReservationService.createReservation(reservation);

        Map<String, String> result = new HashMap<>();
        result.put("message", "예약이 완료되었습니다!");
        return result;
    }
}
