package org.springframework.samples.petclinic.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(authorize -> authorize

				// --- 1. PRODUCT & RECIPE RESTRICTIONS ---

				// DELETE: Restricted only to Admin. Note the use of "*" for the ID.
				.requestMatchers("/products/*/delete", "/product-recipes/*/delete")
				.hasRole("ADMIN")

				// CREATE & EDIT: Both Admin and Manager can access the forms.
				// Added "/products/*/edit" to catch existing product updates.
				.requestMatchers("/products/new", "/products/*/edit", "/product-recipes/new", "/product-recipes/*/edit")
				.hasAnyRole("ADMIN", "MANAGER")

				// FORM SUBMISSIONS: Ensure both roles can POST data back to the server.
				.requestMatchers(HttpMethod.POST, "/products/**", "/product-recipes/**")
				.hasAnyRole("ADMIN", "MANAGER")

				.requestMatchers(HttpMethod.PUT, "/product-recipes/**")
				.hasAnyRole("ADMIN", "MANAGER")

				// --- 2. EXISTING SCHOOL & USER CONFIGS ---

				.requestMatchers("/users/profile", "/users/delete").authenticated()
				.requestMatchers("/schools/new").hasAuthority("MANAGE_ALL_SCHOOLS")
				.requestMatchers(HttpMethod.GET, "/schools", "/schools/{slug:[a-zA-Z-]+}").permitAll()

				// --- 3. PUBLIC & GENERAL ACCESS ---

				.requestMatchers(
					"/", "/register-student", "/register", "/login", "/resources/**",
					"/recipes/**", "/recipes/new", "/pets/**", "/vets/**", "/vets.html",
					"/products",        // List view
					"/products/*",      // Single product detail view
					"/product-recipes",
					"/api/**"
				).permitAll()

				// Fallback for any other GET requests and general authentication
				.requestMatchers(HttpMethod.GET).permitAll()
				.anyRequest().authenticated()
			)
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
