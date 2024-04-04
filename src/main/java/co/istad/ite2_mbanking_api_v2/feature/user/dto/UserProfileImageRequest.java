package co.istad.ite2_mbanking_api_v2.feature.user.dto;

import jakarta.validation.constraints.NotBlank;
public record UserProfileImageRequest(
        @NotBlank(message = "Media is required")
        String mediaName
) {
}
