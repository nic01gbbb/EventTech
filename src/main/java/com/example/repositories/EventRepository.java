package com.example.repositories;

import com.example.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {


    @Query("SELECT e FROM Event e " +
            "WHERE e.date >= current date ")
    public Page<Event> getEventONTime(Pageable pageable);


    @Query(
            "SELECT e FROM Event e LEFT JOIN FETCH e.address a " +
                    "WHERE(:title = '' OR e.title LIKE %:title%) " +
                    "AND (:city = '' OR  a.city  LIKE %:city%) " +
                    "AND (:uf = '' OR  a.uf LIKE %:uf%) " +
                    "AND (e.date>= :iDate AND e.date <= :fDate)")

    public Page<Event> FilterEvents(
            @Param("title") String title
            , @Param("city") String city
            , @Param("uf") String uf
            , @Param("iDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date iDate
            , @Param("fDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fDate
            , Pageable pageable
    );


    @Query("SELECT e FROM Event e LEFT JOIN e.coupons c "
            + "WHERE (e.id = :id) AND (c.valid >= current date ) "
    )
    public Event findEventByCouponValid(@RequestParam("id") UUID id);
}

