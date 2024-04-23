package co.istad.ite2_mbanking_api_v2.auth.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}
