package co.istad.ite2_mbanking_api_v2.feature.accounttype;

import co.istad.ite2_mbanking_api_v2.feature.accounttype.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);
}
