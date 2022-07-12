package com.wj.entity;

public class Address {
    private Integer id;
    private String prov;
    private String city;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", prov='" + prov + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
