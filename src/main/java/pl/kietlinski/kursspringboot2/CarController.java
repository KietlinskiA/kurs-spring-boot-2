package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;
    private List<Car> carList;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
        this.carList = carService.getCarList();
    }

    @GetMapping
    public List<Car> getCars() {
        return carList;
    }

    @GetMapping("/id/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable long carId) {
        Optional<Car> optionalCar = carList.stream().filter(car -> car.getId() == carId).findFirst();
        if(optionalCar.isPresent())
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{carColor}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String carColor) {
        List<Car> foundedCars = carList.stream().filter(car -> Objects.equals(car.getColor(), carColor)).toList();
        if(!foundedCars.isEmpty())
            return new ResponseEntity<>(foundedCars, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car newCar) {
        newCar.setId(carList.size()+1);
        carList.add(newCar);
        Optional<Car> optionalCar = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if(optionalCar.isPresent())
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Car> modifyCar(@RequestBody Car newCar) {
        Optional<Car> optionalCar = carList.stream().filter(car1 -> car1.getId() == newCar.getId()).findFirst();
        if(optionalCar.isPresent()) {
            Car oldCar = optionalCar.get();
            newCar.setId(oldCar.getId());
            carList.remove(oldCar);
            carList.add(newCar);
            return new ResponseEntity<>(newCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity<Car> modifyCarOneField(@RequestBody Car newCar) {
        Optional<Car> optionalCar = carList.stream().filter(car1 -> car1.getId() == newCar.getId()).findFirst();
        if(optionalCar.isPresent()) {
            Car oldCar = optionalCar.get();
            carList.remove(oldCar);
            if(newCar.getMark() != null)
                oldCar.setMark(newCar.getMark());
            if(newCar.getModel() != null)
                oldCar.setModel(newCar.getModel());
            if(newCar.getColor() != null)
                oldCar.setColor(newCar.getColor());
            carList.add(oldCar);
            return new ResponseEntity<>(oldCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{carId}")
    public ResponseEntity<Car> removeCar(@PathVariable long carId) {
        Optional<Car> optionalCar = carList.stream().filter(car1 -> car1.getId() == carId).findFirst();
        if(optionalCar.isPresent()) {
            carList.remove(optionalCar.get());
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
