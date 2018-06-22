package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.loggers.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class App {
    
    private Client client;
    
    private EventLogger eventLogger;
    
    private static ConfigurableApplicationContext ctx;
    
    
    public App() {}
    
    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }
    
    public static void main(String[] args ) {
        ctx = new ClassPathXmlApplicationContext("classpath*:spring.xml");
        App app = ctx.getBean(App.class);
        app.logEvent("Client " + app.client.getId() + " added", EventType.INFO);
        app.logEvent("Client 2 added", EventType. ERROR);
        app.logEvent("Client 35 added", null);
        ctx.close();
    }
    
    public void logEvent(String msg, EventType type) {
        if (EventType.INFO.equals(type)) {
            eventLogger = ctx.getBean(ConsoleEventLogger.class);
        } else if (EventType.ERROR.equals(type)) {
            eventLogger = ctx.getBean(FileEventLogger.class);
        } else {
            eventLogger = ctx.getBean(CombinedEventLogger.class);
        }
        Event evt = ctx.getBean(Event.class);
        evt.setMsg(msg);
        eventLogger.logEvent(evt);
        
    }
    
    
    
}
