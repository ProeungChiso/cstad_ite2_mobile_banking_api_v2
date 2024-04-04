package co.istad.ite2_mbanking_api_v2.feature.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;

@Builder
public record UserCreateRequest(
        @NotNull(message = "Pin is required")
        @Max(value = 9999, message = "Pin must 4 digits")
        @Positive(message = "Pin must be not positive")
        Integer pin,
        @NotBlank(message = "Phone Number is required")
        @Size(max = 20, message = "Phone Number must me less than 20 characters")
        String phoneNumber,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Confirm password is required")
        String confirmedPassword,
        @NotBlank(message = "Name is required")
        @Size(max = 40)
        String name,
        @NotBlank(message = "Gender is required")
        @Size(max = 6)
        String gender,
        @NotNull(message = "Date of birth is required")
        LocalDate dob,
        @NotBlank(message = "National Card ID is required")
        @Size(max = 20, message = "National Card ID must be less than 20 characters")
        String nationalCardId,
        @Size(max = 20, message = "Student Card ID must be less than 20 characters")
        String studentIdCard,
        @NotEmpty(message = "Roles is required")
        List<RoleRequest> roles
) {
}
