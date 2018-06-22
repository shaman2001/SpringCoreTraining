package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;

public class ConsoleEventLogger implements EventLogger {
    
    public ConsoleEventLogger() {
    }
    
    public void logEvent(Event evt){
        System.out.println(evt.toString());
    }
    
    
}
