package com.epam.spring.core;

import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.Event;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.ConsoleEventLogger;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.EnumMap;
import java.util.Map;

@Component("appAnnotation")
public class AppAnnotation {
    
    private Client client;
    
    private EventLogger defaultLogger;
    
    private Map<EventType, EventLogger> loggerMap;
    
    private static ConfigurableApplicationContext ctx;
    
    
    public AppAnnotation() {
        this.client = new Client("1", "Mama Smith");
        this.defaultLogger = new ConsoleEventLogger();
        loggerMap = new EnumMap<>(EventType.class);
    }
    
    public AppAnnotation(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggerMap) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggerMap = new EnumMap<>(loggerMap);
    }
    
    public static void main(String[] args ) {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class, LoggersConfig.class);
        AppAnnotation app = ctx.getBean(AppAnnotation.class);
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
    

    
    
    
}
