package com.example.pio.appweb.internet;

/**
 * Created by pio on 10.08.15.
 */
public class Event {

    private String event_name;
    private String id;

    public Event(String id, String name){

        this.id = id;
        this.event_name = name;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return event_name;
    }

    public void setEventName(String name) {
        this.event_name = name;
    }





}
