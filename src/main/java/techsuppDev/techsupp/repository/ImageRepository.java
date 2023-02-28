package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}