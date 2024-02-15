package com.vs45tech.com.restfulwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"field2","field3"})
public class SomeBean {
private String field1;
@JsonIgnore
private String field2;
private String field3;
    public SomeBean(String string, String string2, String string3) {
     this.field1=string;
     this.field2=string2;
     this.field3=string3;
    }
    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }
    public String getField3() {
        return field3;
    }
    public void setField3(String field3) {
        this.field3 = field3;
    }
    @Override
    public String toString() {
        return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
    }

}
