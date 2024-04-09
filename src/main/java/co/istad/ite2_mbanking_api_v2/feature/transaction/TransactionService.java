package co.istad.ite2_mbanking_api_v2.feature.transaction;

import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest request);

    Page<TransactionResponse> getTransactions(int page, int size, String sort, String transaction_type);
}
