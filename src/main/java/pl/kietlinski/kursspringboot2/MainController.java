package pl.kietlinski.kursspringboot2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class MainController {

    private MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    @AroundCarAspect
    public List<Car> getCars() {
        return mainService.getCarList();
    }

    @PostMapping
    @AfterCarAspect
    public void addCar(@RequestBody Car newCar) {
        mainService.getCarList().add(newCar);
    }

    @DeleteMapping
    @BeforeCarAspect
    public void removeCars() {
        mainService.getCarList().clear();
    }

}
