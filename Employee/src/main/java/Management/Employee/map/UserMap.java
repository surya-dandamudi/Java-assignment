package Management.Employee.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import Management.Employee.model.Users;
import Management.Employee.objects.UsersSignupRequestobj;
import Management.Employee.objects.UsersSignupResponseobj;
@Component
public class UserMap {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMap(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Users toEntity(UsersSignupRequestobj obj) {

        return Users.builder()
                .password(passwordEncoder.encode(obj.getPassword()))
                .username(obj.getUsername())
                .build();
    }

    public UsersSignupResponseobj toDTO(Users user) {

        return UsersSignupResponseobj.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
