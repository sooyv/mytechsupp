package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Getter @Setter
public class UserForm {

    @NotEmpty(message = "사용자 email은 필수입력항목입니다.")
    private String email;

    @NotEmpty(message = "사용자 비밀번호는 필수입력항목입니다.")
    private String password;

}
