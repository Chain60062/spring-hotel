package com.vinicius.springhotel.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.vinicius.springhotel.services.SecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector)
			throws Exception {
		var mvcRequestMatcher = new MvcRequestMatcher.Builder(introspector);
		http.csrf(csrf -> csrf.disable());
		http.headers(frameOptions -> frameOptions.disable());

		http.logout(logout -> logout.logoutUrl("/auth/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID"));

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(mvcRequestMatcher.pattern("/open")).permitAll()
				.requestMatchers(mvcRequestMatcher.pattern("/auth/login")).permitAll()
				.requestMatchers(mvcRequestMatcher.pattern("/auth/register")).permitAll()
				.requestMatchers(PathRequest.toH2Console()).permitAll()
				.anyRequest().authenticated());
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
}