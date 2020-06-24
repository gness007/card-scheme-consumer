package ng.com.gness.cardschemeconsumer.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
public class CardDTO {
    private String scheme;
    private String type;
    private String brand;
    private String iin;
    private String bank;
    private boolean verified;

    public CardDTO(String scheme, String type, String brand, String iin, String bank, boolean verified) {
        this.scheme = scheme;
        this.type = type;
        this.brand = brand;
        this.iin = iin;
        this.bank = bank;
        this.verified = verified;
    }
}
