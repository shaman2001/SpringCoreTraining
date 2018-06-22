package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Hello world!
 *
 */
public class App {
    
    private Client client;
    
    private EventLogger eventLogger;
    
    private static ConfigurableApplicationContext ctx;
    
    
    public App() {
        this.client = new Client("1", "Mama Smith");
        this.eventLogger = new ConsoleEventLogger();
    }
    
    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }
    
    public static void main(String[] args ) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
//        App app = new App();
//        app.eventLogger = new ConsoleEventLogger();
//        app.client = new Client("1", "Papa Smith");
        app.logEvent("Client 1 added");
//        app.logEvent("Client " + app.client.getId() + " added", EventType.INFO);
        app.logEvent("Client 2 added");
        app.logEvent("Client 35 added");
        ctx.close();
    }
    
    public void logEvent(String msg, EventType type) {
//        if (EventType.INFO.equals(type)) {
//            eventLogger = ctx.getBean(ConsoleEventLogger.class);
//        } else if (EventType.ERROR.equals(type)) {
//            eventLogger = ctx.getBean(FileEventLogger.class);
//        } else {
//            eventLogger = ctx.getBean(CombinedEventLogger.class);
//        }
//        Event evt = ctx.getBean(Event.class);
//        evt.setMsg(msg);
//        eventLogger.logEvent(evt);
        
    }
    
    public void logEvent(String msg) {
        Event evt = new Event(new Date(), new SimpleDateFormat("hh:mm:ss"));
        String message = msg.replaceAll(client.getId(), client.getFullName());
        evt.setMsg(message);
        eventLogger.logEvent(evt);
    }
    
    
    
}
