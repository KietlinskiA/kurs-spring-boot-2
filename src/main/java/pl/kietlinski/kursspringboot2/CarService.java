package pl.kietlinski.kursspringboot2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> carList;

    public CarService() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1L, "BMW", "X3", "Black"));
        carList.add(new Car(2L, "BMW", "Z4", "White"));
        carList.add(new Car(3L, "Audi", "A4", "Red"));
        carList.add(new Car(4L, "Audi", "A7", "White"));
        carList.add(new Car(5L, "Mazda", "MX5", "Black"));
    }

    public List<Car> getCarList() {
        return carList;
    }
}
