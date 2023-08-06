package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Profile("Plus")
public class ShopPlus extends ShopStart{

    private String className;
    protected List<Product> productList;
    @Value("${shop-info.vat}")
    protected double vat;
    @Value("${shop-info.locale}")
    private String locale;
    private ReloadableResourceBundleMessageSource source;

    @Autowired
    public ShopPlus(ReloadableResourceBundleMessageSource source) {
        this.source = source;
        className = getClass().getSimpleName();
        productList = getProductList();
    }

    public ShopPlus() {
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo() {
        System.out.println(className);
        for(Product product : productList){
            product.setPrice(product.getPrice() * (1 - (vat/100)));
        }
        System.out.println(source.getMessage("vatInfo", new Object[]{vat}, Locale.forLanguageTag(locale)));
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
