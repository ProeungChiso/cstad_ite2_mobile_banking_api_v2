package co.istad.ite2_mbanking_api_v2.mapper;

import co.istad.ite2_mbanking_api_v2.domain.AccountType;
import co.istad.ite2_mbanking_api_v2.feature.accounttype.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
    List<AccountTypeResponse> toAccountTypeResponse(List<AccountType> accountTypes);
}
