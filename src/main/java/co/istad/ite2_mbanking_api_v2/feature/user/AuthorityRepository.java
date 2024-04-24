package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
