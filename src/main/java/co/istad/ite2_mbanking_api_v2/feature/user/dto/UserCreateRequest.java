package co.istad.ite2_mbanking_api_v2.feature.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record UserCreateRequest(
        @NotNull
        @Max(9999)
        @Positive
        Integer pin,
        @NotBlank
        @Size(max = 20)
        String phoneNumber,
        @NotBlank
        String password,
        @NotBlank
        String confirmedPassword,
        @NotBlank
        @Size(max = 40)
        String name,
        @NotBlank
        @Size(max = 6)
        String gender,
        @NotNull
        LocalDate dob,
        @NotBlank
        @Size(max = 20)
        String nationalCardId,
        @Size(max = 20)
        String studentIdCard
) {
}
