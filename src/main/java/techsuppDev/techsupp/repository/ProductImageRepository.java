package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Image;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductProductIdOrderByImgIdAsc(Long productId);
}
