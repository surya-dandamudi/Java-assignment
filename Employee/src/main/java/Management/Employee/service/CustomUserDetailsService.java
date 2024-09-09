// package Management.Employee.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Primary;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import Management.Employee.model.Users;
// import Management.Employee.repository.UsersRepository;


// @Service

// public class CustomUserDetailsService implements UserDetailsService {

//     @Autowired
//     private UsersRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Users user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

//         // Ensure that getRoles returns a Set<String> or a collection of roles.
//         return new org.springframework.security.core.userdetails.User(
//                 user.getUsername(),
//                 user.getPassword(),
//                 user.isEnabled(),
//                 user.isAccountNonExpired(),
//                 user.isCredentialsNonExpired(),
//                 user.isAccountNonLocked(),
//                 user.getAuthorities() // Ensure that getAuthorities returns a collection of GrantedAuthority
//         );
//     }
// }
