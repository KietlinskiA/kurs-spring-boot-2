package pl.kietlinski.kursspringboot2;

import java.util.List;
import java.util.Map;

// CRUD
public interface CarDao {

    void saveCar(Car newCar);
    List<Map<String, Object>> getCarList();

    Car updateCar(Car newCar);

    void deleteCar(long carId);

}
