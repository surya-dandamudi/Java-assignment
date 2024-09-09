package Management.Employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import Management.Employee.map.UserMap;
import Management.Employee.model.Users;
import Management.Employee.objects.UsersSigninRequestobj;
import Management.Employee.objects.UsersSignupRequestobj;
import Management.Employee.repository.UsersRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UserMap userMap;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UsersService(
            UsersRepository usersRepository,
            UserMap userMap,
            AuthenticationManager authenticationManager) {

        this.usersRepository = usersRepository;
        this.userMap = userMap;
        this.authenticationManager = authenticationManager;
    }

    public Users signUp(UsersSignupRequestobj usersSignupRequestobj) {
        if (usersRepository.existsByUsername(usersSignupRequestobj.getUsername())) {
            throw new EntityExistsException("User with given username already exists.");
        }
        Users user = userMap.toEntity(usersSignupRequestobj);
        return usersRepository.save(user);
    }

    public Users signIn(UsersSigninRequestobj usersSigninRequestobj) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usersSigninRequestobj.getUsername(),
                        usersSigninRequestobj.getPassword()
                )
        );

        return usersRepository.findByUsername(usersSigninRequestobj.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User does not exist with given username."));
    }
}
