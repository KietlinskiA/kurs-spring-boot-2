package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private Card card;

    public CardService(@Qualifier("masterCardService") Card card) {
        this.card = card;
        System.out.println(card.getInfo());
    }
}
