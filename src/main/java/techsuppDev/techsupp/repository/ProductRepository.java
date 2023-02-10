package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

//    하나만 가져오는 것
//    매개 변수에 넣을 것이 pk
//    (이거를 검색 로직에 적용도 가능 할 것 같은데 어떤 매개변수가 들어오는지는
//    컨트롤러에서 해야줘야하나?
//    일단 pk 는 id 로 설정해서 하나만 가져와 보자
//    작동 잘 됨 ㅇㅇ
    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }

//    페이징을 위한 function
//    5개를 가져올 것임
//    public List<Product> findFiveProduct(Long id) {
//        return em.createQuery("select * from Product i", Product.class).getResultList();
//    }


//    모든 데이터 가져오는 것
    public List<Product> findAll() {
        return em.createQuery("select i from Product i", Product.class)
                .getResultList();
    }

//    페이징 용 컬럼에 있는 데이터 갯수만 가져오는 것
    public Object getRow() {
        return em.createQuery("select count(*) from Product")
                .getSingleResult();
    }

}
