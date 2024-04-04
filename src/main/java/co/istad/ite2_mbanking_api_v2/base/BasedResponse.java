package co.istad.ite2_mbanking_api_v2.base;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasedResponse<T> {
    private T payload;
}
