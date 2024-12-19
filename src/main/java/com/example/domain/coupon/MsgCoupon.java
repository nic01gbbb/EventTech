package com.example.domain.coupon;

public class MsgCoupon {

    private String message;
private  Coupon coupon;


public MsgCoupon(String message, Coupon coupon) {
    this.message = message;
    this.coupon = coupon;
}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}



