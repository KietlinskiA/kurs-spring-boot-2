package pl.kietlinski.kursspringboot2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VisaCardService implements Card {

    public VisaCardService() {
        getInfo();
    }

    @Override
    public String getInfo() {
        return "VisaCardService";
    }
}
