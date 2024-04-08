package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import co.istad.ite2_mbanking_api_v2.domain.AccountType;
import co.istad.ite2_mbanking_api_v2.domain.User;
import co.istad.ite2_mbanking_api_v2.domain.UserAccount;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountRenameRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import co.istad.ite2_mbanking_api_v2.feature.accounttype.AccountTypeRepository;
import co.istad.ite2_mbanking_api_v2.feature.user.UserRepository;
import co.istad.ite2_mbanking_api_v2.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{
    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;

    @Override
    public Page<AccountResponse> findList(int page, int size) {
        if(page < 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "page must be a positive integer"
            );
        }
        if(size < 1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "size must be a positive integer"
            );
        }
        Sort sortByName = Sort.by(Sort.Direction.ASC, "actName");
        PageRequest pageRequest = PageRequest.of(page, size, sortByName);
        Page<Account> accounts = accountRepository.findAll(pageRequest);
        return accounts.map(accountMapper::toAccountResponse);
    }

    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Invalid account type"));
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found"));
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo("987654321");
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);
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

    @Override
    public AccountResponse renameByActNo(String actNo, AccountRenameRequest request) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found"
                ));

        if(account.getAlias().equals(request.newName())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "New Name is the same as the old Name"
            );
        }

        account.setAlias(request.newName());
        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);
    }

    @Transactional
    @Override
    public void hideAccount(String actNo) {
        if(!accountRepository.existsByActNo(actNo)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account has not been found"
            );
        }
        try{
            accountRepository.hideAccountByActNo(actNo);
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong" + e.getMessage()
            );
        }
    }
}
