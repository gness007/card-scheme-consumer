package ng.com.gness.cardschemeconsumer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ng.com.gness.cardschemeconsumer.pojo.CardDTO;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name ="verifiedCards")
public class Card {

    public Card(CardDTO pojo) {
        this.scheme = pojo.getScheme();
        this.type = pojo.getType();
        this.brand = pojo.getBrand();
        this.iin = pojo.getIin();
        this.bank = pojo.getBank();
        this.verified = pojo.isVerified();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String scheme;
    private String type;
    private String brand;
    private String iin;
    private String bank;
    private boolean verified;

    public Card(String scheme, String type, String brand, String iin, String bank, boolean verified) {
        this.scheme = scheme;
        this.type = type;
        this.brand = brand;
        this.iin = iin;
        this.bank = bank;
        this.verified = verified;
    }
}
