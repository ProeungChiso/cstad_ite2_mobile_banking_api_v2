package co.istad.ite2_mbanking_api_v2.feature.mail;

import co.istad.ite2_mbanking_api_v2.feature.mail.dto.MailRequest;
import co.istad.ite2_mbanking_api_v2.feature.mail.dto.MailResponse;

public interface MailService {
    MailResponse send(MailRequest mailRequest);
}
