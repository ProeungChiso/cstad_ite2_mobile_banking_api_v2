package co.istad.ite2_mbanking_api_v2.feature.user;

import co.istad.ite2_mbanking_api_v2.base.BasedMessage;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.*;
import org.springframework.data.domain.Page;

public interface UserService {
    void createNew(UserCreateRequest request);
    UserResponse findByUuid(String uuid);
    Page<UserResponse> findList(int page, int limit);
    UserResponse updateByUuid(String uuid, UserUpdateRequest request);
    BasedMessage blockByUuid(String uuid);
    void deleteByUuid(String uuid);
    String updateProfileImage(String mediaName, String uuid);
    void updateUserPasswordByUuid(String uuid, String newPassword);
    void updateUserDetailByUuid(String uuid, UserDetailUpdateRequest request);

}
