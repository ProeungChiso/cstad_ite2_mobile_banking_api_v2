package co.istad.ite2_mbanking_api_v2.init;

import co.istad.ite2_mbanking_api_v2.domain.Role;
import co.istad.ite2_mbanking_api_v2.feature.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;

    @PostConstruct
    void init() {
        if (roleRepository.count() < 1) {
            Role user = new Role();
            user.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role staff = new Role();
            staff.setName("STAFF");

            Role admin = new Role();
            admin.setName("ADMIN");
            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }

    }

}

