package br.senac.tads.dsw.dadospessoais.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap();
		BCryptPasswordEncoder bcryptEnc = new BCryptPasswordEncoder();
		encoders.put("bcrypt", bcryptEnc);
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		var passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(bcryptEnc);
		return passwordEncoder;
	}
	
	@Bean
	AuthenticationManager authenticationManager(UsuarioSistemaService service, PasswordEncoder passowordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(service);
		authProvider.setPasswordEncoder(passowordEncoder);
		return new ProviderManager(authProvider);
	}
	
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.cors(cors -> Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(fo -> fo.sameOrigin()))
				.formLogin(formLogin -> formLogin.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/login", "/login.html", "/me.html",
                                "/h2-console/**",
                                "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
						.permitAll()
						.requestMatchers("/admin").hasAuthority("SCOPE_ADMIM")
						.anyRequest().authenticated())
				.build();
	}
}
