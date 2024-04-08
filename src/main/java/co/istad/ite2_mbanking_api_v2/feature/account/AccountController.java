package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountRenameRequest;
import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    @PutMapping("/{actNo}/rename")
    AccountResponse renameAccount(@PathVariable String actNo, @Valid @RequestBody AccountRenameRequest request) {
        return accountService.renameByActNo(actNo, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }

    @PutMapping("/{actNo}/hide")
    void hideAccountByActNo(@PathVariable String actNo) {
        log.info("Hiding account by actNo: {}", actNo);
        accountService.hideAccount(actNo);
    }

    @GetMapping()
    Page<AccountResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return accountService.findList(page, size);
    }

    @GetMapping("/update/transferLimit")
    void updateTransferLimit(@RequestParam BigDecimal transferLimit){
        log.info("Transfer Limit: {}", transferLimit);
        accountService.updateTransferLimit(transferLimit);
    }
}
