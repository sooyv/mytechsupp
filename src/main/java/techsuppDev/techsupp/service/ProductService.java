package techsuppDev.techsupp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    public final ProductRepository productRepository;

    public List<Product> findProduct() {
        return productRepository.findAll();
    }
}
