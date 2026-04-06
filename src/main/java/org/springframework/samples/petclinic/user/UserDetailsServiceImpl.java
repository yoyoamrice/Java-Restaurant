package org.springframework.samples.petclinic.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));

		if (user.getDeletedAt() != null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}

		// NEW AUTHORITIES LOGIC
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role role : user.getRoles()) {
			// 1. Add the role (Spring Security requires the "ROLE_" prefix for roles)
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

			// 2. Add all permissions attached to this role
			for (Permission permission : role.getPermissions()) {
				authorities.add(new SimpleGrantedAuthority(permission.getName()));
			}
		}

		// UPDATED BUILDER
		return org.springframework.security.core.userdetails.User.builder()
			.username(user.getEmail())
			.password(user.getPassword())
			.authorities(authorities) // Replaced .roles() with .authorities()
			.build();
	}
}
