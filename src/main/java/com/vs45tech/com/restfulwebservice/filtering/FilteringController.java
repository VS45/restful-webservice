package com.vs45tech.com.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
    
    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean(){
        SomeBean someBean=new SomeBean("value1","value2","value3");
        MappingJacksonValue mjv=new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mjv.setFilters(filters);
        return mjv;
    }
    @GetMapping("/filtering-list")
    public MappingJacksonValue getSomeBeanList(){
       List<SomeBean> somebeans=Arrays.asList(new SomeBean("value1","value2","value3"),
       new SomeBean("value4","value5","value6"),
       new SomeBean("value7","value8","value9")) ; 
       MappingJacksonValue mjv=new MappingJacksonValue(somebeans);
       SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field2");
       FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
       mjv.setFilters(filters);
        return mjv;
    }
}
