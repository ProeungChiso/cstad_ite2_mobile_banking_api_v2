package co.istad.ite2_mbanking_api_v2.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountRenameRequest(
        @NotBlank(message = "New name is required")
        @Size(message = "Account name must be less than 100 characters")
        String newName
) {
}
