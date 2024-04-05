package co.istad.ite2_mbanking_api_v2.feature.account.dto;

import co.istad.ite2_mbanking_api_v2.feature.accounttype.dto.AccountTypeResponse;
import co.istad.ite2_mbanking_api_v2.feature.user.dto.UserResponse;
import java.math.BigDecimal;

public record AccountResponse(
        String actNo,
        String actName,
        String alias,
        BigDecimal balance,
        BigDecimal transferLimit,
        AccountTypeResponse accountType,
        UserResponse user

) {
}
