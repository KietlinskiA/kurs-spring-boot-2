package pl.kietlinski.kursspringboot2.gui;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kietlinski.kursspringboot2.menager.CarMenager;
import pl.kietlinski.kursspringboot2.model.Car;

import java.util.List;

@Route("show-cars")
public class CarShowGui extends VerticalLayout {

    private CarMenager carMenager;

    @Autowired
    public CarShowGui(CarMenager carMenager) {
        this.carMenager = carMenager;

        Grid<Car> grid = new Grid<>(Car.class, false);
        grid.setMaxWidth(800, Unit.PIXELS);
        grid.addColumn(Car::getBrand).setHeader("Marka");
        grid.addColumn(Car::getModel).setHeader("Model");
        grid.addColumn(Car::getColor).setHeader("Kolor");

        List<Car> carList = carMenager.getCarList();
        grid.setItems(carList);
        add(grid);
    }
}
