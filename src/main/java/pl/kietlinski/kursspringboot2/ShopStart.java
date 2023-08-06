package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("Start")
public class ShopStart {

    private String className;
    protected List<Product> productList;

    @Autowired
    public ShopStart() {
        className = getClass().getSimpleName();
        productList = getProductList();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo() {
        System.out.println(className);
        System.out.println(productList);
    }

    public List<Product> getProductList() {
        productList = new ArrayList<>();
        productList.add(new Product("TV", 200));
        productList.add(new Product("DVD", 100));
        productList.add(new Product("Xbox", 400));
        return productList;
    }

}
