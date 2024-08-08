package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder().username("user").password(bCryptPasswordEncoder().encode("1234"))
				.authorities("ROLE_USER").build();

		UserDetails admin1 = User.builder().username("admin").password(bCryptPasswordEncoder().encode("1111"))
				.authorities("ROLE_ADMIN").build();

		return new InMemoryUserDetailsManager(user1, admin1);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers(headers -> headers.disable()).csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers("/main").permitAll()
						.requestMatchers("/main/user").hasAuthority("ROLE_USER")
						.requestMatchers("/main/admin").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/main/payment").hasRole("ADMIN")
						.requestMatchers("/main/both").hasAnyRole("ADMIN", "USER")
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}