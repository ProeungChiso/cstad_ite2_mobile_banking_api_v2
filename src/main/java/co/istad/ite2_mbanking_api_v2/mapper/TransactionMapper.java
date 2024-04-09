package co.istad.ite2_mbanking_api_v2.mapper;

import co.istad.ite2_mbanking_api_v2.domain.Transaction;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TransactionMapper.class)
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);
    Transaction fromTransactionResponse(TransactionCreateRequest transactionCreateRequest);
}
