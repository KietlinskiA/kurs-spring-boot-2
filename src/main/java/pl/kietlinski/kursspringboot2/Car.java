package pl.kietlinski.kursspringboot2;

import java.time.Year;

public class Car {
    private long id;
    private String brand;
    private String model;
    private int production_year;

    public Car(long id, String brand, String model, int production_year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.production_year = production_year;
    }

    public Car(String brand, String model, int production_year) {
        this.brand = brand;
        this.model = model;
        this.production_year = production_year;
    }

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProduction_year() {
        return production_year;
    }

    public void setProduction_year(int production_year) {
        this.production_year = production_year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", production_year=" + production_year +
                '}';
    }
}
