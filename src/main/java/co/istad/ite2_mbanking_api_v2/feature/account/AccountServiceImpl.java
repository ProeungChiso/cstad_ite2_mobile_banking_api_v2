package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import co.istad.ite2_mbanking_api_v2.domain.AccountType;
import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.UserRepository;
import co.istad.ite2_mbanking_api_v2.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;

    @Override
    public void createNew(AccountCreateRequest request) {

    }

    @Override
    public AccountResponse findByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account No is invalid!"
                ));
        return accountMapper.toAccountResponse(account);
    }
}
