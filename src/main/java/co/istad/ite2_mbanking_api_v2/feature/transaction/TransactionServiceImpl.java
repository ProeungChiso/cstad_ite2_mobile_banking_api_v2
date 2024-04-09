package co.istad.ite2_mbanking_api_v2.feature.transaction;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import co.istad.ite2_mbanking_api_v2.domain.Transaction;
import co.istad.ite2_mbanking_api_v2.feature.account.AccountRepository;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionResponse;
import co.istad.ite2_mbanking_api_v2.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Transactional
    @Override
    public TransactionResponse transfer(TransactionCreateRequest request) {
        Account owner = accountRepository.findByActNo(request.ownerActNo())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account owner not found"
                ));

        Account transferReceiver = accountRepository.findByActNo(request.transferReceiverActNo())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transfer receiver not found"
                ));

        if(owner.getBalance().doubleValue() < request.amount().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transfer amount not enough"
            );
        }

        if(request.amount().doubleValue() > owner.getTransferLimit().doubleValue()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transfer amount exceeds transfer limit"
            );
        }

        owner.setBalance(owner.getBalance().subtract(request.amount()));

        transferReceiver.setBalance(transferReceiver.getBalance().add(request.amount()));

        Transaction transaction = transactionMapper.fromTransactionResponse(request);

        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);

        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getTransactions() {
        return List.of();
    }
}
