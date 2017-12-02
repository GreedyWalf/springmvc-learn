package com.qs.mvc.service;

import org.springframework.stereotype.Service;

@Service(value = "demoService")
public class DemoService {

    public String saySomething() {
        return "hello";
    }
}
