package com.example.domain.event;

import com.example.domain.event.Event;

public class EventMsg {

    public String msg;
    public Event event;


    public EventMsg(String msg, Event event) {
        this.msg = msg;
        this.event = event;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}