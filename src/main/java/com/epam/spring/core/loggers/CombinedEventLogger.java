package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("combinedLogger")
@Scope("singleton")
public class CombinedEventLogger implements EventLogger {
    
    private List<EventLogger> loggerList;
    
    @Autowired
    @Resource(name = "consoleLogger")
    private ConsoleEventLogger consoleLogger;
    
    @Autowired
    @Resource(name = "cacheFileLogger")
    private CacheFileEventLogger cacheFileLogger;
    
    public CombinedEventLogger() {
    }
    
    @PostConstruct
    public void init() {
        loggerList = new ArrayList<>(3);
        loggerList.add(consoleLogger);
        loggerList.add(cacheFileLogger);
    }
    
    public CombinedEventLogger(List<EventLogger> loggerList) {
        this.loggerList = loggerList;
    }
    
    public void logEvent(Event evt) {
        loggerList.forEach(logger -> logger.logEvent(evt));
    }
    
    
}
