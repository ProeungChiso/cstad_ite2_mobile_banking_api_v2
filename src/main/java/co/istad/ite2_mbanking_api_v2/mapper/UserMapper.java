package co.istad.ite2_mbanking_api_v2.mapper;

import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserDetailsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest request);
    UserDetailsResponse userDetailResponse(User user);
}
