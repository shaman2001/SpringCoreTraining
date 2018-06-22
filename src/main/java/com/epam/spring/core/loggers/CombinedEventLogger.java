package com.epam.spring.core.loggers;

import java.util.List;

public class CombinedEventLogger implements EventLogger {
    
    
    private List<EventLogger> loggerList;
    
    public CombinedEventLogger(List<EventLogger> loggerList) {
        this.loggerList = loggerList;
    }
    
    public void logEvent(Event evt) {
        loggerList.forEach(logger-> logger.logEvent(evt));
    }
    
    
}
