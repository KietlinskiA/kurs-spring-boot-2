package pl.kietlinski.kursspringboot2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
@Tag(
        name = "Car Controller all CRUD API",
        description = "This is the class that implements all the CRUD api related to car menagement"
)
public class CarController {

    private CarService carService;
    private List<Car> carList;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
        this.carList = carService.getCarList();
    }

    @Operation(
            summary = "Get list of cars"
    )
    @GetMapping
    public List<Car> getCars() {
        return carList;
    }

    @Operation(
            summary = "Get car by ID"
    )
    @GetMapping("/id/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable long carId) {
        Optional<Car> optionalCar = carList.stream().filter(car -> car.getId() == carId).findFirst();
        if(optionalCar.isPresent())
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get car by color"
    )
    @GetMapping("/color/{carColor}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String carColor) {
        List<Car> foundedCars = carList.stream().filter(car -> Objects.equals(car.getColor(), carColor)).toList();
        if(!foundedCars.isEmpty())
            return new ResponseEntity<>(foundedCars, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Add new car to list"
    )
    @PostMapping
    public ResponseEntity<Car> addCar(@Validated @RequestBody Car newCar) {
        newCar.setId(carList.size()+1);
        carList.add(newCar);
        Optional<Car> optionalCar = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if(optionalCar.isPresent())
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(
            summary = "Modifu car by ID"
    )
    @PutMapping
    public ResponseEntity<Car> modifyCar(@Validated @RequestBody Car newCar) {
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

    @Operation(
            summary = "Modify one parameter in car by ID"
    )
    @PatchMapping
    public ResponseEntity<Car> modifyCarOneField(@RequestBody Car newCar) {
        Optional<Car> optionalCar = carList.stream().filter(car1 -> car1.getId() == newCar.getId()).findFirst();
        if(optionalCar.isPresent()) {
            Car oldCar = optionalCar.get();
            carList.remove(oldCar);
            if(newCar.getBrand() != null)
                oldCar.setBrand(newCar.getBrand());
            if(newCar.getModel() != null)
                oldCar.setModel(newCar.getModel());
            if(newCar.getColor() != null)
                oldCar.setColor(newCar.getColor());
            carList.add(oldCar);
            return new ResponseEntity<>(oldCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Remove car from the list"
    )
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
