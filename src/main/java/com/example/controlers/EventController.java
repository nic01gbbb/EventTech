package com.example.controlers;


import com.example.domain.event.*;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<EventMsg> create(@RequestParam("title") String title,
                                           @RequestParam(value = "description", required = false) String description,
                                           @RequestParam("date") Long date,
                                           @RequestParam("city") String city,
                                           @RequestParam("state") String state,
                                           @RequestParam("remote") Boolean remote,
                                           @RequestParam("eventurl") String eventurl,
                                           @RequestParam(value = "image", required = false) MultipartFile image) {
        {
            EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, state, remote, eventurl, image);
            Event newEvent = this.eventService.createEvent(eventRequestDTO).getEvent();
             String msg = this.eventService.createEvent(eventRequestDTO).getMsg();
            return ResponseEntity.ok(new EventMsg(msg, newEvent));
        }

    }

    @GetMapping("/forpage")
    public List<EventResponseDTO> geteventsPerpage(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return this.eventService.getEvents(page, size);


    }

    @GetMapping("/GetEventOnTime")
    public List<EventResponseDTO> getEventDateUpTime(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size
    ) {
        return eventService.getEventOnTime(page, size);

    }

    @GetMapping("/filter")
    public List<EventResponseDTO> getEventFilter(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(required = false) String title,
                                                 @RequestParam(required = false) String city,
                                                 @RequestParam(required = false) String state,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date iDate,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fDate
    ) {


        return eventService.FilterEvents(page, size, title, city, state,
                iDate, fDate);

    }

    @GetMapping("/forId")
    public ResponseEntity<EventMsg> getEventDetails(@RequestParam(required = false) UUID id) {
        try {
            return ResponseEntity.ok(eventService.getEventDetails(id));
        } catch (Error e) {
            EventMsg eventMsg = new EventMsg(e.getMessage(), null);
            return ResponseEntity.badRequest().body(eventMsg);
        }
    }


}