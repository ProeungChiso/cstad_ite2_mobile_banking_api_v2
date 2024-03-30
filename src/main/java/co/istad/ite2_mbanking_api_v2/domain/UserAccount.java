package co.istad.ite2_mbanking_api_v2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isDeleted;
    private Boolean isBlocked;
    private LocalDateTime createAt;

    @ManyToOne
    private Account account;

    @ManyToOne
    private User user;
}
