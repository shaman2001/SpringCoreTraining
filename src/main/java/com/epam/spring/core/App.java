package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


/**
 * Hello world!
 *
 */
public class App {
    
    private Client client;
    
    private EventLogger defaultLogger;
    
    private Map<EventType, EventLogger> loggerMap;
    
    private static ConfigurableApplicationContext ctx;
    
    
    public App() {
        this.client = new Client("1", "Mama Smith");
        this.defaultLogger = new ConsoleEventLogger();
        loggerMap = new EnumMap<>(EventType.class);
    }
    
    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggerMap) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggerMap = new EnumMap<>(loggerMap);
    }
    
    public static void main(String[] args ) {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.logEvent("Client 1 added", EventType.ERROR);
        app.logEvent("Client 2 added", EventType.INFO);
        app.logEvent("Client 35 added", null);
        ctx.close();
    }
    
    public void logEvent(String msg, EventType type) {
        EventLogger logger;
        if (type!=null) {
            logger = loggerMap.get(type);
        } else {
            logger = defaultLogger;
        }
        Event evt = ctx.getBean(Event.class);
        String message = msg.replaceAll(client.getId(), client.getFullName());
        evt.setMsg(message);
        logger.logEvent(evt);
        
    }
    
//    public void logEvent(String msg) {
//        Event evt = new Event(new Date(), new SimpleDateFormat("hh:mm:ss"));
//        String message = msg.replaceAll(client.getId(), client.getFullName());
//        evt.setMsg(message);
//        defaultLogger.logEvent(evt);
//    }
    
    
    
}
