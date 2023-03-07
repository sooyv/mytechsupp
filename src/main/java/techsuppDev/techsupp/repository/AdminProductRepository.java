package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Product;

@Repository
public interface AdminProductRepository extends JpaRepository<Product,Long> {
}