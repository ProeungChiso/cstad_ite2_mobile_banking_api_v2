package co.istad.ite2_mbanking_api_v2.feature.transaction;

import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionCreateRequest;
import co.istad.ite2_mbanking_api_v2.feature.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest request){
        return transactionService.transfer(request);
    }

    @GetMapping()
    Page<TransactionResponse> getAllTransactions(@RequestParam(required = false) String sort,
                                                 @RequestParam(required = false) String transactionType,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "1") int size
                                                 )
    {
        return transactionService.getTransactions(page, size, sort, transactionType);
    }
}
