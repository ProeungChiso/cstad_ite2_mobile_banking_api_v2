package co.istad.ite2_mbanking_api_v2.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Phone Number is required!")
        String phoneNumber,
        @NotBlank(message = "Password is required!")
        String password
) {
}
