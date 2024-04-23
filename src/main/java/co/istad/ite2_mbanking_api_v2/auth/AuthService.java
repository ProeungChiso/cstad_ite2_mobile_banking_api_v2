package co.istad.ite2_mbanking_api_v2.auth;

import co.istad.ite2_mbanking_api_v2.auth.dto.AuthResponse;
import co.istad.ite2_mbanking_api_v2.auth.dto.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
}
