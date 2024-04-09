package co.istad.ite2_mbanking_api_v2.feature.transaction;

import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest request);
    List<TransactionResponse> getTransactions();
}
