package com.example.testdatabase;

public class Car {

    private String Model ;
    private String color ;
    private String tigerPlate ;
    private String id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car() {

    }

    public Car(String model, String color, String tigerPlate) {
        this.Model = model;
        this.color = color;
        this.tigerPlate = tigerPlate;
    }

    public Car (String model, String color ,String tigerPlate,String id ) {
        this.id = id ;
        this.Model=model;
        this.color = color;
        this.tigerPlate = tigerPlate;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTigerPlate() {
        return tigerPlate;
    }

    public void setTigerPlate(String tigerPlate) {
        this.tigerPlate = tigerPlate;
    }
}
