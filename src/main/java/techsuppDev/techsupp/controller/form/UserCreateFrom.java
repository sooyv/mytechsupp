package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Getter @Setter
public class UserCreateFrom {

//    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 이름은 필수입력항목입니다.")
    private String userName;

    @NotEmpty(message = "사용자 email은 필수입력항목입니다.")
    private String email;

    @NotEmpty(message = "사용자 비밀번호는 필수입력항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수입력항목입니다.")
    private String password2;

    @NotEmpty(message = "핸드폰 번호 입력은 필수입력항목입니다.")
    private String userPhone;

//    @Column(nullable = false)
//    private String userName;
//
//    @Column(unique = true, nullable = false) // 유일한 값만 저장 가능. 중복 저장 불가
//    private String email;
//
//    private String password;
//
//    @Column(nullable = false)
//    private String userPhonenum;

}
