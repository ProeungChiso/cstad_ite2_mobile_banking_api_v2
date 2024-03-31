package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByStudentIdCard(String studentIdCard);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNationalCardId(String nationalIdCard);
}
