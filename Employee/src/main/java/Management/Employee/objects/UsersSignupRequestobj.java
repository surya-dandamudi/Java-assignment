package Management.Employee.objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UsersSignupRequestobj {
    

    @NotBlank(message = "username can't be blank.")
    private String username;

    @NotBlank(message = "password can't be blank.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "Password must be 8-20 characters long, include at least one digit, one uppercase letter, one lowercase letter, and one special character.")
    private String password;

    public String getPassword(){
        return this.password;
    }
    public String getUsername(){
        return this.username;
    }
}
