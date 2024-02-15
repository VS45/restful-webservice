package com.vs45tech.com.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
    
    @GetMapping("/filtering")
    public SomeBean getSomeBean(){
        return new SomeBean("value1","value2","value3");
    }
    @GetMapping("/filtering-list")
    public List<SomeBean> getSomeBeanList(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),
        new SomeBean("value4","value5","value6"),
        new SomeBean("value7","value8","value9")) ;
    }
}
