package co.istad.ite2_mbanking_api_v2.feature.account;

import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo) {
        return accountService.findByActNo(actNo);
    }
}
