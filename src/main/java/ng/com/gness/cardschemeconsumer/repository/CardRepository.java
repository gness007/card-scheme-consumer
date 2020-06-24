package ng.com.gness.cardschemeconsumer.repository;

import ng.com.gness.cardschemeconsumer.model.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
}
