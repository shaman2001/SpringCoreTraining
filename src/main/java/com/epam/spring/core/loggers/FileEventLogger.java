package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component("fileLogger")
@Scope("singleton")
public class FileEventLogger implements EventLogger {
    
    @Value("${file.name}")
    private String filename;
    
    private File file;
    
    
    public FileEventLogger() {
    
    }
    
    public FileEventLogger(String filename) {
        this.filename = filename;
    }
    
    @PostConstruct
    public void init() {
        file = new File(filename);
        if (file.exists() && !file.canWrite()) throw new RuntimeException("the file doesn't have write access");
        
    }
    
    public void logEvent(Event evt) {
        try {
            FileUtils.writeStringToFile(file, evt.toString() + "\n", true);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    
}
