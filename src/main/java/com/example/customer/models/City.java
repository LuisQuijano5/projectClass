package com.example.customer.models;

public class City {
    private int id;
    private String name;
    private String district;
    private Country country;

    public City() {
    }
    public City(int id, String name, String district, Country country) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.country = country;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
