package co.istad.ite2_mbanking_api_v2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentReceiver;
    private BigDecimal amount;
    @Column(columnDefinition = "TEXT")
    private String remark;
    @Column(nullable = false, length = 50)
    private String transactionType;
    private Boolean status;
    private LocalDateTime transactionAt;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Account transferReceiver;
}
