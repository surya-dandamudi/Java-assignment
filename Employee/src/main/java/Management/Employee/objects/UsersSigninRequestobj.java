package Management.Employee.objects;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UsersSigninRequestobj {
     @NotBlank(message = "username can't be blank.")
    private String username;

    @NotBlank(message = "password can't be blank.")
    private String password;
}
