package com.vs45tech.com.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class HelloWorldController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/basicauth")
    public String basicAuth() {
        return "Success";
    }
    @GetMapping("/hello-world")
    public String helloWOrld() {
        return "hello World";
    }
    
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World, "+name);
    }
    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale=LocaleContextHolder.getLocale();
     return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
     //   return "hello World v2";
    }
}
