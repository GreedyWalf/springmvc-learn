package com.qs.mvc.entity;

public class DemoObj{
    private Long id;
    private String name;

    //使用jsckson对对象和json数据转换时需要默认无参构造函数
    public DemoObj(){

    }

    public DemoObj(Long id,String name){
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}