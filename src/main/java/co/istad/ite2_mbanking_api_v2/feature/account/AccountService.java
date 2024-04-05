package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;

public interface AccountService {
    void createNew(AccountCreateRequest request);
    AccountResponse findByActNo(String actNo);
}
