package com.epam.spring.core;

import com.epam.spring.core.beans.Event;
import com.epam.spring.core.loggers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggersConfig {
    
    @Bean(name = "consoleLogger")
    public EventLogger consoleLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(name = "fileLogger", initMethod = "init")
    public EventLogger fileLogger() {
        return new FileEventLogger();
    }
    
    @Bean(name = "cacheFileLogger", initMethod = "init", destroyMethod = "destroy")
    public EventLogger cacheFileLogger() {
        return new CacheFileEventLogger();
    }
    
    @Bean(name = "combinedLogger")
    public EventLogger combinedLogger() {
        return new CombinedEventLogger();
    }
    
    @Bean(name = "event")
    public Event event() {
        return new Event();
    }
    
    

}
