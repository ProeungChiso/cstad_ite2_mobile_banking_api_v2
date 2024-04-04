package co.istad.ite2_mbanking_api_v2.feature.media.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(
        String name, //unique
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String contentType, //png, jpeg, mp4
        String extension,
        String uri, // https...
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long size
) {
}
