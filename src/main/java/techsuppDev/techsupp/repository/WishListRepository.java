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
    public void wishInsert(String userId, Long productId) {
        String sql = "" +
                "insert into wish_list (product_id, user_id) " +
                "values (" + productId + ", " + userId + ")";

        System.out.println("sql : " + sql);
        Query nativeQuery = em.createNativeQuery(sql, WishList.class);
        nativeQuery.executeUpdate();
        String result = "즐겨찾기에 추가 되었습니다.";
        System.out.println(result);
    }

    @Transactional
    @Modifying
    public void wishDelete(String userId, Long productId) {
        String sql = "" +
                "delete from wish_list " +
                "where product_id = '" + productId + "' " +
                "and user_id = '" + userId + "';";

        System.out.println("sql: " + sql);
        Query nativeQuery = em.createNativeQuery(sql, WishList.class);
        nativeQuery.executeUpdate();
        String result = "즐겨찾기가 취소 되었습니다.";

        System.out.println(result);

    }

}
