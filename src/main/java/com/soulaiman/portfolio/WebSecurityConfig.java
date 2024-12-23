package com.soulaiman.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf
		.ignoringRequestMatchers("/chatbot") // Disable CSRF for /chatbot
		)
		.headers(headers -> headers
				.frameOptions(frameOptions -> frameOptions
					.sameOrigin()
				)
		)
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/", "/error", "/public/**", "/chatbot").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("/login")
			.permitAll()
		)
		.logout((logout) -> logout.permitAll());

		return http.build();
	}

    @Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withUsername("admin")
				.password("{bcrypt}$2a$10$IDgZMldRTnPueihyfY3e3.XbdUQknNNYmj8BtVQCEksslgs6puClu")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

}
