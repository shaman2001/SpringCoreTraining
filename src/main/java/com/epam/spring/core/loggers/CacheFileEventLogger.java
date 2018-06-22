package com.epam.spring.core.loggers;



import com.epam.spring.core.beans.Event;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Lazy
public class CacheFileEventLogger extends FileEventLogger {
    
    private int cacheSize;
    
    private List<Event> cache;
    
    public CacheFileEventLogger(String filename, Integer cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
    }
    
    @Override
    public void init() {
        super.init();
        cache = new ArrayList<>(cacheSize);
        
    }
    
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
