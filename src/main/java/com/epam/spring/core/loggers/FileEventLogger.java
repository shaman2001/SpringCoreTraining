package com.epam.spring.core.loggers;

import com.epam.spring.core.beans.Event;
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
