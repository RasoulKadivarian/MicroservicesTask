package ir.hamrahlotus.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(0)
    private Long balance;

    public Wallet(Long balance) {
        this.balance = balance;
    }

    public void incrementWalletBalance(Long amount) {
        this.balance += amount;
    }

    public void decrementWalletBalance(Long amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
        }
    }
}
