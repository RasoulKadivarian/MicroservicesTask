package ir.hamrahlotus.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Transaction(Long userId, Long amount, Type type) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
    }

    @PrePersist
    public void prePersist() {
        this.date = new Date();
    }
}
