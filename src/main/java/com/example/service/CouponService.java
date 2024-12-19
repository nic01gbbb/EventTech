package com.example.service;

import com.example.domain.coupon.Coupon;
import com.example.domain.coupon.CouponRequestDTO;
import com.example.domain.coupon.MsgCoupon;
import com.example.domain.event.Event;
import com.example.repositories.CouponRepository;
import com.example.repositories.EventRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class CouponService {


    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    @Autowired
    CouponRepository couponRepository;


    @Autowired
    EventRepository eventRepository;


    public MsgCoupon createCoupon(CouponRequestDTO coupon) throws Exception {

        Event event = eventRepository.findById(coupon.event())
                .orElseThrow(() -> new Exception("Evento não encontrado com ID: " + coupon.event()));

        List<Coupon> Allcoupons = couponRepository.findAll();
        for (Coupon c : Allcoupons) {
            if (c.getEvent().equals(event)) {
                return new MsgCoupon("This coupon already exists", null);
            }

        }
        Date newdate = coupon.valid();
        Coupon newcoupon = new Coupon(coupon.code(), coupon.discount(), newdate, event);
        couponRepository.save(newcoupon);
        return new MsgCoupon("Coupon registered successfully", newcoupon);

    }


}
