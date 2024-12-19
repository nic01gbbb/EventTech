package com.example.domain.coupon;
import com.example.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Setter
@Getter
@Table(name = "coupon")
@Entity
@AllArgsConstructor


public class Coupon {

    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private Integer discount;
    private Date valid;


    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    public Coupon() {
    }

    public Coupon(String code, Integer discount, Date valid, Event event) {
        this.code = code;
        this.discount = discount;
        this.valid = valid;
        this.event = event;
    }


    public UUID getEvent() {
        return event.getId();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getValid() {
        return valid;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }
}

