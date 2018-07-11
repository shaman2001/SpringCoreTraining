package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component("cacheFileLogger")
@Scope("singleton")
public class CacheFileEventLogger extends FileEventLogger {
    
    @Value("5")
    private int cacheSize;
    
    private List<Event> cache;
    
    public CacheFileEventLogger() {
    
    }
    
    public CacheFileEventLogger(String filename, Integer cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }
    
    @PostConstruct
    @Override
    public void init() {
        super.init();
        cache = new ArrayList<>(cacheSize);
        
    }
    
    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeCache();
        }
        
    }
    
    
    @Override
    public void logEvent(Event evt) {
        cache.add(evt);
        if (cache.size()==cacheSize) {
            writeCache();
        }
        
        
    }
    
    private void writeCache() {
        Iterator<Event> it = cache.iterator();
        while (it.hasNext()) {
            super.logEvent(it.next());
            it.remove();
        }
    }
    
    
    
}
