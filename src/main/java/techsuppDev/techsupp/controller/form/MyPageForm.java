package techsuppDev.techsupp.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Data
public class MyPageForm {

    @NotEmpty(message = "사용자 email은 필수입력항목입니다.")
    private String userEmail;

    @NotEmpty(message = "사용자 Name은 필수입력항목입니다.")
    private String userName;

    @NotEmpty(message = "사용자 연락처는 필수항목입니다.")
    private String userPhone;

    @NotEmpty(message = "사용자 비밀번호는 필수입력항목입니다.")
    private String userPassword;
}
