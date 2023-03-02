package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 모든 회원 정보 찾기
    List<User> findAll();

    // email로 회원 찾기
    List<User> findByUserEmail(String userEmail);

    // 회원 한명 조회
    User getOne(Long userId);

}
