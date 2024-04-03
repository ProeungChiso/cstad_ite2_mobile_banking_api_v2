package co.istad.ite2_mbanking_api_v2.feature.accounttype;

import co.istad.ite2_mbanking_api_v2.feature.accounttype.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    @GetMapping
    List<AccountTypeResponse> findList(){
        return accountTypeService.findList();
    }
    @GetMapping("/{alias}")
    AccountTypeResponse findByAlias(@PathVariable String alias){
        return accountTypeService.findByAlias(alias);
    }
}
