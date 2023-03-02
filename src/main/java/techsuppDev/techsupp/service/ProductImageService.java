package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.repository.ProductImageRepository;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

}
