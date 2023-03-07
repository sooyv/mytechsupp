package techsuppDev.techsupp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
//
//    @Query("SELECT w FROM wish_list w WHERE w.user_id =: userId")
//    Optional<WishList> findByUserId(@Param("userId") Long userId);

//        @Query("SELECT w FROM wish_list w WHERE w.user_id = ?1")
    @Query("SELECT w FROM WishList w WHERE w.userId = :userId")
    Optional<WishList> findByUserId(@Param("userId") Long userId);
    }


