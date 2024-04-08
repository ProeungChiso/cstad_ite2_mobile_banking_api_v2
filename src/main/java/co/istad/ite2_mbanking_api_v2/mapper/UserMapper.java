package co.istad.ite2_mbanking_api_v2.mapper;

import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.domain.UserAccount;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserDetailsResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserCreateRequest(UserCreateRequest request);
    UserDetailsResponse userDetailResponse(User user);
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }
    UserResponse toUserResponse(User user);

}
