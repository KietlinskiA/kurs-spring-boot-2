package pl.kietlinski.kursspringboot2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

public abstract class Shop {

    protected final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Value("${shop-info.locale}")
    protected String locale;
    @Value("${shop-info.owner}")
    protected String owner;

    @Autowired
    protected ReloadableResourceBundleMessageSource source;

    protected abstract void getInfo();

    protected List<Product> getProductList() {
        logger.info("Product list generation");
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("TV", 200));
        productList.add(new Product("DVD", 100));
        productList.add(new Product("Xbox", 400));
        if (productList.size() > 0) {
            logger.info("Product list generated");
        } else {
            logger.error("Product list not generated");
            System.exit(1);
        }
        return productList;
    }

}
