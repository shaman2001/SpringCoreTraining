package com.epam.spring.core.loggers;

public class ConsoleEventLogger implements EventLogger {
    
    
    public void logEvent(Event evt){
        System.out.println(evt.toString());
    }
    
    
}
