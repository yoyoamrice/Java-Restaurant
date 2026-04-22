package org.springframework.samples.petclinic.user; // Ensure this is correct!

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable) // Fixed the disable syntax
			.authorizeHttpRequests(authorize -> authorize

				// --- 1. PRODUCT & RECIPE RESTRICTIONS (First Priority) ---

				// DELETE: Only Admin
				.requestMatchers("/products/{id}/delete", "/product-recipes/{id}/delete")
				.hasRole("ADMIN")

				// CREATE & EDIT: Admin and Manager
				.requestMatchers("/products/new", "/products/{id}/edit", "/product-recipes/new")
				.hasAnyRole("ADMIN", "MANAGER")

				// POST/PUT: Ensure they can actually save the forms
				.requestMatchers(HttpMethod.POST, "/products/**", "/product-recipes/**")
				.hasAnyRole("ADMIN", "MANAGER")
				.requestMatchers(HttpMethod.PUT, "/product-recipes/**")
				.hasAnyRole("ADMIN", "MANAGER")

				// --- 2. EXISTING SCHOOL & USER CONFIGS ---

				.requestMatchers("/users/profile", "/users/delete")
				.authenticated()
				.requestMatchers("/schools/new")
				.hasAuthority("MANAGE_ALL_SCHOOLS")
				.requestMatchers(HttpMethod.GET, "/schools", "/schools/{slug:[a-zA-Z-]+}")
				.permitAll()

				// --- 3. PUBLIC & GENERAL ACCESS ---

				.requestMatchers("/", "/register-student", "/register", "/login", "/resources/**", "/recipes/**",
						"/recipes/new", "/pets/**", "/vets/**", "/vets.html", "/products", // List
																							// view
																							// is
																							// public
						"/products/{id}", // Single product view is public
						"/product-recipes", "/api/**")
				.permitAll()

				// Fallback for any other GET requests
				.requestMatchers(HttpMethod.GET)
				.permitAll()
				// Everything else requires authentication
				.anyRequest()
				.authenticated())
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(form -> form.loginPage("/login")
				.usernameParameter("email")
				.defaultSuccessUrl("/login-success", true)
				.failureHandler((request, response, exception) -> {
					request.getSession().setAttribute("LAST_EMAIL", request.getParameter("email"));
					response.sendRedirect("/login?error");
				})
				.permitAll())
			.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());

		return http.build();
	}

}
