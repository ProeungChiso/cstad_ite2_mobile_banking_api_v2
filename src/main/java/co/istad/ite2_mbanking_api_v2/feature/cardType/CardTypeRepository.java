package co.istad.ite2_mbanking_api_v2.feature.cardType;

import co.istad.ite2_mbanking_api_v2.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTypeRepository extends JpaRepository<CardType, Long> {
}
