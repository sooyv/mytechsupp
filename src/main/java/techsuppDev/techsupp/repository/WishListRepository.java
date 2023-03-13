package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.domain.WishList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class WishListRepository {
    private final EntityManager em;

    @Transactional
    @Modifying
    public String wishInsert(String userId, Long productId) {
        String sql = "" +
                "insert into wish_list (product_id, user_id) " +
                "values (" + userId + ", " + productId + ")";
        Query nativeQuery = em.createNativeQuery(sql, WishList.class);
        nativeQuery.executeUpdate();
        return null;
    }

    @Transactional
    @Modifying
    public String wishDelete(String userId, Long productId) {
        String sql = "" +
                "delete wish_list" +
                "where product_id = " + productId + "" +
                "and user_id = " + userId + ";";
        Query nativeQuery = em.createNativeQuery(sql, WishList.class);
        nativeQuery.executeUpdate();
        return null;

    }

}
