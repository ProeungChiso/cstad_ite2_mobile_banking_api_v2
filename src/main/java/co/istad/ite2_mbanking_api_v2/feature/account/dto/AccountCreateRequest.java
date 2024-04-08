package co.istad.ite2_mbanking_api_v2.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,
        @NotNull
        BigDecimal balance,
        @NotBlank
        String accountTypeAlias,
        @NotBlank
        String userUuid,
        String cardNumber
) {
}
