package co.istad.ite2_mbanking_api_v2.feature.transaction.dto;

import co.istad.ite2_mbanking_api_v2.feature.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
    AccountSnippetResponse owner,
    AccountSnippetResponse transferReceiver,
    BigDecimal amount,
    String remark,
    String transactionType,
    Boolean status,
    LocalDateTime transactionAt
) {
}
