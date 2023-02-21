package techsuppDev.techsupp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 모든 회원 정보 찾기
    List<User> findAll();

    // email로 회원 찾기
    List<User> findByUserEmail(String userEmail);

}
