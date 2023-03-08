package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data

@Getter @Setter
public class UserForm {

    private String email;
    private String password;
    private String userName;
    private String userPhone;
    private String userRole;

}
