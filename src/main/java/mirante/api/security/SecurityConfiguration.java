package mirante.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
      .csrf(csrf -> csrf.ignoringRequestMatchers("/auth/**"))
      .securityContext(context -> context.securityContextRepository(
          new RequestAttributeSecurityContextRepository())
      ).authorizeHttpRequests(request -> request
                .requestMatchers("/version", "/auth/**", "/api-docs/**")
                .permitAll()
                .anyRequest().authenticated()

      // .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil))
      // .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
    );

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
       AccountDetailsService accountDetailsService,
      PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(accountDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);

    return new ProviderManager(authenticationProvider);
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return SecUtils.encoder;
  }

}
