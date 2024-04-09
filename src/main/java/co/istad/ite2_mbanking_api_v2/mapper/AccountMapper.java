package co.istad.ite2_mbanking_api_v2.mapper;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountSnippetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountTypeMapper.class
})
public interface AccountMapper {

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
    @Mapping(source = "userAccountList", target = "user",
    qualifiedByName = "mapUserResponse" )
    AccountResponse toAccountResponse(Account account);
    AccountSnippetResponse toAccountSnippetResponse(Account account);
}
