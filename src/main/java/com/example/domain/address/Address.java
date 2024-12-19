package com.example.domain.address;
import com.example.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Table(name = "address")
@Entity
@AllArgsConstructor

public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Address() {

    }

    public Address(String city, String uf, Event event) {
        this.city = city;
        this.uf = uf;
        this.event = event;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
