package com.example.domain.event;

import com.example.domain.address.Address;
import com.example.domain.coupon.Coupon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Table(name = "event")
@Entity
public class Event {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String title;
    private String description;
    private String imgurl;
    private String eventurl;
    private Boolean remote;
    private Date date;


    @JsonIgnoreProperties({"event_id"})
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Coupon> coupons;


    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"event_id,city,uf,id"})
    private Address address;

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public Event(String title, String description, String eventurl, Date date, String imgurl, Boolean remote) {
        this.title = title;
        this.description = description;
        this.eventurl = eventurl;
        this.imgurl = imgurl;
        this.date = date;
        this.remote = remote;
    }

    public Event() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getEventurl() {
        return eventurl;
    }

    public void setEventurl(String eventurl) {
        this.eventurl = eventurl;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Address getAddress() {
        return address;
    }

    public String addresscity() {
        return address.getUf();
    }

    public String addressstate() {
        return address.getUf();
    }


}





