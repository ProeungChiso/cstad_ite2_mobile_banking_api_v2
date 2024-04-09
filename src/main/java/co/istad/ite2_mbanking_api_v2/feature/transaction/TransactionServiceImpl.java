package co.istad.ite2_mbanking_api_v2.feature.transaction;

import co.istad.ite2_mbanking_api_v2.domain.Account;
import co.istad.ite2_mbanking_api_v2.domain.Transaction;
import co.istad.ite2_mbanking_api_v2.feature.account.AccountRepository;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionResponse;
import co.istad.ite2_mbanking_api_v2.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Page<TransactionResponse> getTransactions(int page, int size, String sort, String transaction_type) {

        //check page and size
        if(page < 0 || size < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "page must be a positive integer, and size must be a positive integer"
            );
        }

        PageRequest pageRequest;

        // check if key sort is not null and empty
        if (sort != null && !sort.isEmpty()) {
            Sort.Direction direction = Sort.Direction.DESC;
            if (sort.equalsIgnoreCase("asc")) {
                direction = Sort.Direction.ASC;
            }
            pageRequest = PageRequest.of(page, size, Sort.by(direction, "transactionAt"));
        } else {
            // If key sort has no value
            pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "transactionAt"));
        }
        Page<Transaction> transactions;

        //check transaction if not empty
        if (transaction_type != null && !transaction_type.isEmpty()) {
            //display transfer or payment
            String transactionType = transaction_type.toUpperCase();
            transactions = transactionRepository.findByTransactionType(transactionType, pageRequest);
        } else {
            //display all
            transactions = transactionRepository.findAll(pageRequest);
        }

        //check transactions
        if (transactions.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No transactions found");
        }

        //return page
        return transactions.map(transactionMapper::toTransactionResponse);
    }

}
