package pl.kietlinski.kursspringboot2;

import org.springframework.stereotype.Service;

@Service
public class MasterCardService implements Card {

    public MasterCardService() {
        getInfo();
    }

    @Override
    public String getInfo() {
        return "MasterCardService";
    }
}
