package pl.kietlinski.kursspringboot2;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Profile("Pro")
public class ShopPro extends Shop {

    private final List<Product> productList;

    public ShopPro() {
        this.productList = getProductList();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo() {
        logger.info(getClass().getSimpleName());
        for(Product product : productList){
            product.setPrice(product.getPrice() * (1 - (vat/100)));
            product.setPrice(product.getPrice() * (1 - (discount/100)));
        }
        System.out.println(source.getMessage("vatInfo", new Object[]{vat}, Locale.forLanguageTag(locale)));
        System.out.println(source.getMessage("discountInfo", new Object[]{discount}, Locale.forLanguageTag(locale)));
        System.out.println(productList);
    }

}
