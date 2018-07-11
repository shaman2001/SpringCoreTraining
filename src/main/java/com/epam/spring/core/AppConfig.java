package com.epam.spring.core;


import com.epam.spring.core.beans.Client;
import com.epam.spring.core.beans.EventType;
import com.epam.spring.core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.epam.spring.core.beans.EventType.ERROR;
import static com.epam.spring.core.beans.EventType.INFO;

@Configuration
@Import(LoggersConfig.class)
public class AppConfig {
    
    @Autowired
    @Resource(name="client")
    private Client client;
    
    @Autowired
    @Resource(name="fileLogger")
    private EventLogger defaultLogger;
    
    @Autowired
    @Resource(name="consoleLogger")
    private EventLogger consoleLogger;
    
    @Autowired
    @Resource(name="combinedLogger")
    private EventLogger combinedlogger;
    
    @Bean(name = "propertyPlaceholderConfigurer")
    public static PropertyPlaceholderConfigurer ppConfigurer() {
        PropertyPlaceholderConfigurer ppConfigurer = new PropertyPlaceholderConfigurer();
        ppConfigurer.setLocations(new ClassPathResource("client.properties"), new ClassPathResource("app.properties"));
        ppConfigurer.setIgnoreResourceNotFound(true);
        ppConfigurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return ppConfigurer;
    }
    
    @Bean(name = "appAnnotation")
    public AppAnnotation appAnnotation() {
        Map<EventType,EventLogger> loggerMap = new HashMap<>();
        loggerMap.put(INFO, consoleLogger);
        loggerMap.put(ERROR, combinedlogger);
        return new AppAnnotation(client, defaultLogger, loggerMap);
    }
    
    @Bean(name = "client")
    public Client client() {
        return new Client();
    }
    
    
    
    
    


}
