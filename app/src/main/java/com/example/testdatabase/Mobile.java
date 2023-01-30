package com.example.testdatabase;

public class Mobile {

    private String company ;
    private String color ;
    private String price ;

    public Mobile() {
    }

    public Mobile(String company, String color, String price) {
        this.company = company;
        this.color = color;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
