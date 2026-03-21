package com.example.meetingroom.controller;

import com.example.meetingroom.service.MeetingReservationService;
import com.example.meetingroom.domain.MeetingReservation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins="http://localhost:8081")
public class MeetingReservationController {
    private final MeetingReservationService meetingReservationService;

    public MeetingReservationController(MeetingReservationService meetingReservationService){
        this.meetingReservationService = meetingReservationService;
    }

    @GetMapping
    public List<MeetingReservation> getReservations(
        @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date)
    {
        return meetingReservationService.getReservationByDate(date);
    }
    @PostMapping
    public Map<String, String> createReservation(@RequestBody MeetingReservation reservation){
        meetingReservationService.createReservation(reservation);

        Map<String, String> result = new HashMap<>();
        result.put("message", "예약이 완료되었습니다!");
        return result;
    }
    @DeleteMapping("/{id}")
    public Map<String, String> deletedReservation(@PathVariable("id") Integer id){
        meetingReservationService.deleteReservation(id);

        Map<String, String> result = new HashMap<>();
        result.put("message", "예약이 삭제되었습니다.");
        return result;
    }
}
