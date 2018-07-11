package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("consoleLogger")
@Scope("singleton")
public class ConsoleEventLogger implements EventLogger {
    
    public ConsoleEventLogger() {
    }
    
    public void logEvent(Event evt){
        System.out.println(evt.toString());
    }
    
    
}
