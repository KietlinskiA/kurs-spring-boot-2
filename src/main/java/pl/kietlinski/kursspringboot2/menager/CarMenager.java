package pl.kietlinski.kursspringboot2.menager;

import org.springframework.stereotype.Service;
import pl.kietlinski.kursspringboot2.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarMenager {

    private List<Car> carList;

    public CarMenager() {
        this.carList = new ArrayList<>();
        carList.add(new Car("BMW", "X6", "white"));
        carList.add(new Car("Audi", "A2", "black"));
        carList.add(new Car("Tesla", "Y", "blue"));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void addCar(Car car) {
        carList.add(car);
    }
}
