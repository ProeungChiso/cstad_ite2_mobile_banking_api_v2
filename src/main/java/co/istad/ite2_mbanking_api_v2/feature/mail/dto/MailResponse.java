package co.istad.ite2_mbanking_api_v2.feature.mail.dto;

import jakarta.validation.constraints.NotBlank;

public record MailResponse(
        @NotBlank
        String msg
) {
}
