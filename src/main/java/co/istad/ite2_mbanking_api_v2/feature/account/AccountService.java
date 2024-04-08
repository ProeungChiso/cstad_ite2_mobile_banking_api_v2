package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountRenameRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

public interface AccountService {
    Page<AccountResponse> findList(int page, int size);
    void createNew(AccountCreateRequest request);
    AccountResponse findByActNo(String actNo);
    AccountResponse renameByActNo(String actNo, AccountRenameRequest request);
    void hideAccount(String actNo);
}
