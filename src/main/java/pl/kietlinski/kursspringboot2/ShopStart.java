package pl.kietlinski.kursspringboot2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("Start")
public class ShopStart extends Shop {

    private final List<Product> productList;

    public ShopStart() {
        this.productList = getProductList();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo() {
        logger.info(getClass().getSimpleName());
        System.out.println(productList);
    }

}
