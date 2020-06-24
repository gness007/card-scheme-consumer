package ng.com.gness.cardschemeconsumer.services;

import ng.com.gness.cardschemeconsumer.model.Card;
import ng.com.gness.cardschemeconsumer.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CardService {

    private CardRepository cardRepository;
    @Autowired
    CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void save(Card card) {
        cardRepository.save(card);
    }

}
