package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.Product;

public interface AdminProductRepository extends JpaRepository<Product,Long> {
}
