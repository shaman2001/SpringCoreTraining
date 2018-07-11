package com.epam.spring.core.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Client {
    
    @Value("${id}")
    private String id;
    
    @Value("${name}")
    private String fullName;
    
    @Value("${greeting}")
    private String greeting;
    
    public Client() {}
    
    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getGreeting() {
        return greeting;
    }
    
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
