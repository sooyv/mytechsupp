package techsuppDev.techsupp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.domain.WishList;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyPageRepository extends JpaRepository<WishList, Long> {
    //JPA 선택된 즐겨찾기를 만들 Repository

    List<WishList> findAll();
    Optional<WishList> findByUserId(Long userId);







    //email로 조인할 수 잇는 쿼리를 JPA




//    Product findByUserProduct(long productId);
}
