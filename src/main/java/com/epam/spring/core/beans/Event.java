package com.epam.spring.core.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

@Component("event")
@Scope("prototype")
public class Event {
    
    public static final String BEGIN_DAY_TIME = "08:00:00";
    public static final String END_DAY_TIME = "16:59:59";
    
    private int id;
    
    private String msg;
    
    private Date date;
    
    private DateFormat format;
    
    public Event() {
        date = new Date();
        format = DateFormat.getDateTimeInstance();
    }
    
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
    
    public static Boolean isDay() {
        LocalTime currLocalTime = LocalTime.now();
        LocalTime endDayTime = LocalTime.parse(END_DAY_TIME);
        LocalTime beginDayTime = LocalTime.parse(BEGIN_DAY_TIME);
        return currLocalTime.isBefore(endDayTime) && currLocalTime.isAfter(beginDayTime);
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
