package pl.kietlinski.kursspringboot2.gui;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kietlinski.kursspringboot2.menager.CarMenager;
import pl.kietlinski.kursspringboot2.model.Car;

@Route("add-car")
public class CarAddGui extends VerticalLayout {

    private CarMenager carMenager;

    @Autowired
    public CarAddGui(CarMenager carMenager) {
        this.carMenager = carMenager;
        TextField textFieldBrand = new TextField("Marka", "Audi");
        TextField textFieldModel = new TextField("Model", "A6");
        ComboBox<String> comboBoxColor = new ComboBox<>("Kolor");
        comboBoxColor.setItems("white", "black", "blue");

//        comboBoxColor.setItemLabelGenerator(Car::getColor);

        Button buttonAddCar = new Button("Dodaj");
        buttonAddCar.addClickListener(buttonClickEvent -> {
            if (textFieldBrand.isEmpty() || textFieldModel.isEmpty() || comboBoxColor.isEmpty()){
                Notification notification = Notification.show("Wypełnij wszystkie pola!");
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            } else {
                Car newCar = new Car(textFieldBrand.getValue(), textFieldModel.getValue(), comboBoxColor.getValue());
                carMenager.addCar(newCar);
                Notification notification = Notification.show("Dodano! "
                        + newCar.getBrand() + " " + newCar.getModel() + ", " + newCar.getColor() + ".");
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
            }
        });

        add(textFieldBrand);
        add(textFieldModel);
        add(comboBoxColor);
        add(buttonAddCar);
    }
}
