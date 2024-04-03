package co.istad.ite2_mbanking_api_v2.feature.accounttype.dto;

public record AccountTypeResponse(
        String alias,
        String name,
        String description
) {
}
