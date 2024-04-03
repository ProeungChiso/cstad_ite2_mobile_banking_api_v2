package co.istad.ite2_mbanking_api_v2.feature.media.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        String name, //unique
        String contentType, //png, jpeg, mp4
        String extension,
        String uri, // https...
        Long size
) {
}
