package co.istad.ite2_mbanking_api_v2.mapper;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.domain.UserAccount;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "userAccountList", target = "user",
    qualifiedByName = "mapUserResponse" )
    AccountResponse toAccountResponse(Account account);

    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }
    UserResponse toUserResponse(User user);
}
