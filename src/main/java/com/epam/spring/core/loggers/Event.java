package com.epam.spring.core.loggers;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    
    private int id;
    
    private String msg;
    
    private Date date;
    
    private DateFormat format;
    
    public Event() {
    }
    
    public Event(Date date, DateFormat format) {
        this.date = date;
        this.format = format;
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
                ", msg='" + msg + '\'' +
                ", date=" + format.format(date) +
                '}';
    }
    
    
}
