package pl.kietlinski.kursspringboot2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(
        description = "This is class Car"
)
public class Car {

    private long id;
    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, message = "Brand should be longer than 2 letters")
    private String brand;
    @NotNull(message = "Model cannot be null")
    private String model;
    @NotNull(message = "Color cannot be null")
    @Size(min = 2, message = "Color name should be longer than 2 letters")
    private String color;

    public Car(long id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    @JsonCreator
    public Car(@JsonProperty("mark") String brand,
               @JsonProperty("model") String model,
               @JsonProperty("color") String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                '}';
    }
}
