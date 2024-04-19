package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByStudentIdCard(String studentIdCard);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNationalCardId(String nationalIdCard);
    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    Optional<User> findByUuid(String uuid);
    boolean existsByUuid(String uuid);
    @Modifying
    @Query("UPDATE User AS u SET u.isBlocked = TRUE WHERE u.uuid = ?1")
    void blockByUuid(String uuid);

    Optional<User> findByPhoneNumber(String username);
}
