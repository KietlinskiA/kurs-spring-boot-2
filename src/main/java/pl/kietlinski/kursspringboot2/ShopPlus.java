package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Profile("Plus")
public class ShopPlus extends Shop {

    private final List<Product> productList;
    @Value("${shop-info.vat}")
    protected double vat;

    public ShopPlus() {
        this.productList = getProductList();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo() {
        logger.info(getClass().getSimpleName());
        String shopOwnerText = source.getMessage("shopOwnerInfo", new Object[]{}, Locale.forLanguageTag(locale));
        System.out.println(shopOwnerText + ": " + owner);
        for(Product product : productList){
            product.setPrice(product.getPrice() * (1 - (vat/100)));
        }
        System.out.println(source.getMessage("vatInfo", new Object[]{vat}, Locale.forLanguageTag(locale)));
        System.out.println(productList);
    }

}
