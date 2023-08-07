package pl.kietlinski.kursspringboot2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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
        String shopOwnerText = source.getMessage("shopOwnerInfo", new Object[]{}, Locale.forLanguageTag(locale));
        System.out.println(shopOwnerText + ": " + owner);
        System.out.println(productList);
    }

}
