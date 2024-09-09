package Management.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Management.Employee.map.UserMap;
import Management.Employee.model.Users;
import Management.Employee.objects.UsersSigninRequestobj;
import Management.Employee.objects.UsersSigninResponseobj;
import Management.Employee.objects.UsersSignupRequestobj;
import Management.Employee.service.JwtService;
import Management.Employee.service.UsersService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/auth")
public class UserController {
    private final UsersService usersService;
    private final JwtService jwtService;
    private final UserMap userMap;

    @Autowired
    public UserController(
            UsersService usersService,
            JwtService jwtService,
            UserMap userMap) {

        this.usersService = usersService;
        this.jwtService = jwtService;
        this.userMap = userMap;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UsersSignupRequestobj usersSignupRequestobj) {
        Users user = usersService.signUp(usersSignupRequestobj);
        return new ResponseEntity<>(userMap.toDTO(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody UsersSigninRequestobj usersSigninRequestobj) {
        Users user = usersService.signIn(usersSigninRequestobj);
        UsersSigninResponseobj usersSigninResponseobj = UsersSigninResponseobj.builder()
                .token(jwtService.generateToken(user))
                .expirationTime(jwtService.getExpirationTime())
                .build();
        return new ResponseEntity<>(usersSigninResponseobj, HttpStatus.ACCEPTED);
    }
}
