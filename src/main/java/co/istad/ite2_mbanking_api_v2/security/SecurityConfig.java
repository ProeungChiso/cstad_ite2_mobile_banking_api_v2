package co.istad.ite2_mbanking_api_v2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    //Create user with InMemoryUserDetailsManager
//    @Bean
//    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails userAdmin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        UserDetails userEditor = User.builder()
//                .username("editor")
//                .password(passwordEncoder.encode("editor"))
//                .roles("EDITOR")
//                .build();
//        UserDetails userUser = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build();
//        UserDetails userStaff = User.builder()
//                .username("staff")
//                .password(passwordEncoder.encode("staff"))
//                .roles("STAFF")
//                .build();
//        UserDetails userCustomer = User.builder()
//                .username("customer")
//                .password(passwordEncoder.encode("customer"))
//                .roles("CUSTOMER")
//                .build();
//
//        manager.createUser(userAdmin);
//        manager.createUser(userEditor);
//        manager.createUser(userUser);
//        manager.createUser(userStaff);
//        manager.createUser(userCustomer);
//
//        return manager;
//    }


    //Create user with DaoAuthenticationProvider
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //TODO: your security logic

        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyRole("STAFF", "ADMIN")
                        .requestMatchers("/api/v1/accounts/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/transactions/**").hasRole("USER")
                        .requestMatchers("/api/v1/medias/**").hasRole("CUSTOMER")
                        .anyRequest()
                        .authenticated()
                );

        http.httpBasic(Customizer.withDefaults());

        //Disable CSRF
        http.csrf(token -> token.disable());
        //Change to STATELESS
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
