package com.example.domain.coupon;

import com.example.domain.event.Event;

import java.util.Date;
import java.util.UUID;

public record CouponRequestDTO(String code, Integer discount, Date valid,UUID event) {


}
