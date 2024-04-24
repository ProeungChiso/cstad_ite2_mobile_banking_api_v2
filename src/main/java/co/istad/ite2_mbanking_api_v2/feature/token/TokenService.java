package co.istad.ite2_mbanking_api_v2.feature.token;

import co.istad.ite2_mbanking_api_v2.auth.dto.AuthResponse;
import org.springframework.security.core.Authentication;

public interface TokenService {
    AuthResponse createToken(Authentication auth);

    String createAccessToken(Authentication auth);

    String createRefreshToken(Authentication auth);

}
