package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserCreateRequest;

public interface UserService {
    void createNew(UserCreateRequest request);
}
