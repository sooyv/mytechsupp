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


    List<WishList> findAll();

    @Query("SELECT w FROM WishList w WHERE w.userId = :userId")
    List<WishList> findByUserId(@Param("userId") Long Id);


}
