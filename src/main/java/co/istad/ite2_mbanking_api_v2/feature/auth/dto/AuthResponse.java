package co.istad.ite2_mbanking_api_v2.feature.auth.dto;

public record AuthDto(
        String type,
        String accessToken,
        String refreshToken
) {
}
