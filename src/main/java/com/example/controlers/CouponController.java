package com.example.controlers;


import com.example.domain.coupon.Coupon;
import com.example.domain.coupon.CouponRequestDTO;
import com.example.domain.coupon.MsgCoupon;
import com.example.domain.event.Event;
import com.example.domain.event.EventRequestDTO;
import com.example.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;


    @PostMapping("/create")
    public ResponseEntity<MsgCoupon> createcoupon(@RequestBody CouponRequestDTO couponRequestDTO) {
        try {
            String Msg = couponService.createCoupon(couponRequestDTO).getMessage();
            Coupon coupon = couponService.createCoupon(couponRequestDTO).getCoupon();
            return ResponseEntity.ok(new MsgCoupon(Msg, coupon));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }


    }
}




