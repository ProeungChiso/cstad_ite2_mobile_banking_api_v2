package co.istad.ite2_mbanking_api_v2.init;

import co.istad.ite2_mbanking_api_v2.domain.AccountType;
import co.istad.ite2_mbanking_api_v2.domain.Authority;
import co.istad.ite2_mbanking_api_v2.domain.CardType;
import co.istad.ite2_mbanking_api_v2.domain.Role;
import co.istad.ite2_mbanking_api_v2.feature.account.AccountRepository;
import co.istad.ite2_mbanking_api_v2.feature.accounttype.AccountTypeRepository;
import co.istad.ite2_mbanking_api_v2.feature.cardType.CardTypeRepository;
import co.istad.ite2_mbanking_api_v2.feature.user.AuthorityRepository;
import co.istad.ite2_mbanking_api_v2.feature.user.RoleRepository;
import co.istad.ite2_mbanking_api_v2.feature.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CardTypeRepository cardTypeRepository;
    private final AuthorityRepository authorityRepository;
    @PostConstruct
    void initRole() {

        if (roleRepository.count() < 1) {

            Authority userRead = new Authority();
            userRead.setName("user:read");
            Authority userWrite = new Authority();
            userWrite.setName("user:write");

            Authority transactionRead = new Authority();
            transactionRead.setName("transaction:read");
            Authority transactionWrite = new Authority();
            transactionWrite.setName("transaction:write");

            Authority accountRead = new Authority();
            accountRead.setName("account:read");
            Authority accountWrite = new Authority();
            accountWrite.setName("account:write");

            Authority accountTypeRead = new Authority();
            accountTypeRead.setName("account:type:read");
            Authority accountTypeWrite = new Authority();
            accountTypeWrite.setName("account:type:write");

            Role user = new Role();
            user.setName("USER");
            user.setAuthorities(
                    List.of(
                            userRead,
                            transactionRead,
                            accountRead,
                            accountTypeRead
                    )
            );

            Role customer = new Role();
            customer.setName("CUSTOMER");
            customer.setAuthorities(
                    List.of(
                            userWrite,
                            transactionWrite,
                            accountTypeWrite
                    )
            );


            Role staff = new Role();
            staff.setName("STAFF");
            staff.setAuthorities(
                    List.of(
                            accountTypeWrite
                    )
            );

            Role admin = new Role();
            admin.setName("ADMIN");
            admin.setAuthorities(
                    List.of(
                            userWrite,
                            accountWrite,
                            accountTypeWrite
                    )
            );

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }
    }

    @PostConstruct
    void initAccountType() {
        if (accountTypeRepository.count() < 1) {
            AccountType savingActType = new AccountType();
            savingActType.setName("Saving Account");
            savingActType.setAlias("saving-account");
            savingActType.setIsDeleted(false);
            savingActType.setDescription("A savings account is a deposit account held at a financial institution that provides security for your principal and a modest interest rate.");
            accountTypeRepository.save(savingActType);

            AccountType payrollActType = new AccountType();
            payrollActType.setName("Payroll Account");
            payrollActType.setAlias("payroll-account");
            payrollActType.setIsDeleted(false);
            payrollActType.setDescription("A payroll account is a type of account used specifically for employee compensation, whether it's to do with salary, wage, or bonuses.");
            accountTypeRepository.save(payrollActType);

            AccountType cardActType = new AccountType();
            cardActType.setName("Card Account");
            cardActType.setAlias("card-account");
            cardActType.setIsDeleted(false);
            cardActType.setDescription("Card Account means the Cardholder's Account(s) with the Bank in respect of which the Card is issued, on which withdrawals/payments shall be debited and lodgements credited when effected by the Cardholder.");
            accountTypeRepository.save(cardActType);
        }
    }


    @PostConstruct
    void initCardType() {
        if (cardTypeRepository.count() < 1) {
            CardType masterCard = new CardType();
            masterCard.setName("Master Card");
            masterCard.setIsDeleted(false);

            CardType visaCard = new CardType();
            visaCard.setName("Visa Card");
            visaCard.setIsDeleted(false);

            cardTypeRepository.save(masterCard);
            cardTypeRepository.save(visaCard);
        }
    }
}


