package com.example.TestString.models;

public class CustomerObject {
    public String query;

    public Integer page;

    public Integer size;


    public CustomerObject(String query, Integer page, Integer size){
        this.query = query;
        this.page = page;
        this.size = size;
    }
}
