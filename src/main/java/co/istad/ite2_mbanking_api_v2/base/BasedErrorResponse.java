package co.istad.ite2_mbanking_api_v2.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasedErrorResponse {
    private BasedError error;
}
