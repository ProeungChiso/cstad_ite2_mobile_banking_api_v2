package co.istad.ite2_mbanking_api_v2.feature.user.dto;


import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String name,
        LocalDate dob,
        String profileImage,
        String phoneNumber,
        String studentIdCard
) {
}
