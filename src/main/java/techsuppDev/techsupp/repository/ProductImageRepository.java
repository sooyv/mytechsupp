package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Image;
import techsuppDev.techsupp.service.ProductImageService;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductIdOrderByIdAsc(Long id);


}
