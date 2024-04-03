package co.istad.ite2_mbanking_api_v2.feature.user.dto;

import java.time.LocalDate;

public record UserUpdateRequest(
        String name,
        LocalDate dob,
        String phoneNumber,
        String studentIdCard
) {
}
