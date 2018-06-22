package com.epam.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    
    private int id;
    
    private String msg;
    
    private Date date;
    
    private DateFormat format;
    
//    public Event() {
//    }
    
    public Event(Date date, DateFormat format) {
        this.date = date;
        this.format = format;
        this.id = new Random().nextInt(55);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public Event setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + format.format(date) +
                ", msg='" + msg + '\'' +
                '}';
    }
    
    
}
