package techsuppDev.techsupp.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {

    private String userName;
    private String email;
    private String authNum;
    private String password;
    private String checkPassword;
    private String userPhone;
}
