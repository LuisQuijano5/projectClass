package com.example.customer.models;

public class Address {
    private int id;
    private String name;
    private String pc;
    private City city;

    public Address() {
    }

    public Address(int id, String name, String pc, City city) {
        this.id = id;
        this.name = name;
        this.pc = pc;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
