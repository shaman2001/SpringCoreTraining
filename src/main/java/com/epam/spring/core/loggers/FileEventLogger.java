package com.epam.spring.core.loggers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    
    private String filename;
    
    private File file;
    
    
    public FileEventLogger(String filename) {
        this.filename = filename;
    }
    
    public void init() {
        file = new File(filename);
        if (file.canWrite()) throw new RuntimeException("the file does. not have write access");
        
    }
    
    public void logEvent(Event evt) {
        try {
            FileUtils.writeStringToFile(file, evt.toString(), true);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    
}
