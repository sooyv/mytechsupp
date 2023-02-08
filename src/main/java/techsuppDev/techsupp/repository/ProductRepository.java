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

    public List<Product> findAll() {
        return em.createQuery("select i from Product i", Product.class)
                .getResultList();
    }

//    public List<Product> find() {
//        return em.createQuery()
//    }
}
