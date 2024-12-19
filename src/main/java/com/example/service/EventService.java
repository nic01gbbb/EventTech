package com.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.DemoApplication;
import com.example.domain.address.Address;
import com.example.domain.event.*;
import com.example.repositories.AddressRepository;
import com.example.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;


@Service
public class EventService {


    @Value("${aws.bucket.name}")
    private String eventostecbucket;


    @Autowired
    EventRepository eventRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private AmazonS3 s3client;


    public EventMsg createEvent(EventRequestDTO data) {
        List<Event> allevents = eventRepository.findAll();


        for (Event e : allevents) {
            if (e.getTitle().equals(data.title())) {
                return new EventMsg("This event title already exists", null);
            }
            if (e.getDescription().equals(data.description())) {
                return new EventMsg("This event description already exists", null);
            }
            if (e.getEventurl().equals(data.eventUrl())) {
                return new EventMsg("This event url already exists", null);
            }
        }

        String imgUrl = null;
        if (data.image() != null) {
            imgUrl = this.uploadingImg(data.image());
        }

        Event newEvent = new Event(data.title(), data.description(), data.eventUrl()
                , new Date(data.date()), imgUrl, data.remote());

        eventRepository.save(newEvent);

        Address address = new Address(data.city(), data.state(), newEvent);
        addressRepository.save(address);

        return new EventMsg("Event created successfully", newEvent);

    }

    private String uploadingImg(MultipartFile multipartFile) {


        String fileName = UUID.randomUUID() + "" + multipartFile.getOriginalFilename();
        try {
            File file = this.convertMuiltipartFile(multipartFile);
            s3client.putObject(eventostecbucket, fileName, file);
            file.delete();
            System.out.println("foi ");
            return s3client.getUrl(eventostecbucket, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir o arquivo");
            return e.getMessage();
        }
    }

    private File convertMuiltipartFile(MultipartFile multipartFile) throws Exception {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        System.out.println(convFile);

        return convFile;
    }


    public List<EventResponseDTO> getEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> pageevent = this.eventRepository.findAll((pageable));
        return pageevent.map(event -> new EventResponseDTO(event.getId(), event.getTitle(), event.getDescription(),
                event.getDate(), "", "",
                event.getRemote(),
                event.getEventurl(), event.getImgurl())).toList();
    }


    public List<EventResponseDTO> getEventOnTime(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> pageevent = this.eventRepository.getEventONTime(pageable);
        return pageevent.map(event -> new EventResponseDTO(event.getId(), event.getTitle(), event.getDescription(),
                event.getDate(), "", "",
                event.getRemote(),
                event.getEventurl(), event.getImgurl())).toList();
    }

    public List<EventResponseDTO> FilterEvents(int page, int size, String title, String city,
                                               String state, Date iDate, Date fDate) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> pageEvent = this.eventRepository.FilterEvents(
                title != null ? title : "",
                city != null ? city : "",
                state != null ? state : "",
                iDate,
                fDate,
                pageable);

        return pageEvent.map(event -> new EventResponseDTO(event.getId(), event.getTitle(), event.getDescription(),
                event.getDate(), event.getAddress().getCity(), event.getAddress().getUf(),
                event.getRemote(),
                event.getEventurl(), event.getImgurl())).toList();


    }

    public EventMsg getEventDetails(UUID id) {
        List<Event> Allevents = eventRepository.findAll();
        Boolean mySentence = false;
        for (Event e : Allevents) {
            if (e.getId().equals(id)) {
                mySentence = true;
            }
        }
        if (!mySentence) {
            return new EventMsg("event not found", null);
        }
        Event event = eventRepository.findEventByCouponValid(id);

        if (event == null) {
            return new EventMsg("event not contains coupon registered", null);
        }
        return new EventMsg("ok", event);
    }


}