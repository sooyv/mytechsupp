package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.PageRequestDTO;
import techsuppDev.techsupp.DTO.PageResultDTO;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.controller.HomeController;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.AdminProductRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository adminProductRepository;
    public Long register(ProductDTO productDTO) {
        Product product = productDTO.dtoToEntity(productDTO);
        adminProductRepository.save(product);

        return product.getId();
    };

    public PageResultDTO<ProductDTO, Product> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<Product> result = adminProductRepository.findAll(pageable);
        Function<Product, ProductDTO> fn = (entity -> ProductDTO.entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    };



    // 소영 main page - random product
    public List<Product> getRandomProduct() {
        List<Product> allProducts = adminProductRepository.findAll();
        List<Product> randomProducts = new ArrayList<>(allProducts);
        Collections.shuffle(randomProducts);
        return randomProducts.subList(0, 5);
    }
}