package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false) // 유일한 값만 저장 가능. 중복 저장 불가
    private String userEmail;

    private String userPassword;

    @Column(nullable = false)
    private String userPhone;

    private String role;        //ROLE_USER, ROLE_ADMIN

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions = new ArrayList<>();

    public void updatePassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void updatePhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void updateUsername(String userPhone) {
        this.userPhone = userPhone;
    }

}
