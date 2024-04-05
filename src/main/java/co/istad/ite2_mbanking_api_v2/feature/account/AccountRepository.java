package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByActNo(String actNo);
}
