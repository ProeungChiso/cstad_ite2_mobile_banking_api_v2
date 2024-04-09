package co.istad.ite2_mbanking_api_v2.feature.transaction;

import co.istad.ite2_mbanking_api_v2.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
