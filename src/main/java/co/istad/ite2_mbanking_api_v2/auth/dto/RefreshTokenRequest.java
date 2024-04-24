package co.istad.ite2_mbanking_api_v2.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Refresh Token is required")
        String refreshToken
) {
}
