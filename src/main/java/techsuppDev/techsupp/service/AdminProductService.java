package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.PageRequestDTO;
import techsuppDev.techsupp.DTO.PageResultDTO;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.DTO.ProductImgDTO;
import techsuppDev.techsupp.domain.Image;
import techsuppDev.techsupp.controller.HomeController;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.AdminProductRepository;
import techsuppDev.techsupp.repository.ProductImageRepository;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdminProductService {
    private final AdminProductRepository adminProductRepository;
    private final ProductImageService productImageService;
    private final ProductImageRepository productImageRepository;

    public Long register(ProductDTO productDTO, List<MultipartFile> multipartFileList) throws Exception {
        Product product = productDTO.dtoToEntity(productDTO);
        adminProductRepository.save(product);

        for (int i = 0, max = multipartFileList.size(); i < max; i++) {
            Image image = null;

            if(productDTO.getProductImgDTOList().size() != 0){
                image = Image.builder()
                        .product(product)
                        .repImg(i == 0 ? "Y" : "N")
                        .id(productDTO.getProductImgDTOList().get(0).getId())
                        .build();
            } else{
                image = Image.builder()
                        .product(product)
                        .repImg(i == 0 ? "Y" : "N")
                        .build();
            }
            productImageService.saveImg(image, multipartFileList.get(i));
        }
        return product.getId();
    };

    @Transactional(readOnly = true)
    public ProductDTO getProductDetail(Long id) {
        List<Image> imageList = productImageRepository.findByProductIdOrderByIdAsc(id);
        List<ProductImgDTO> productImgDTOList = new ArrayList<>();

        for (Image image : imageList) {
            ProductImgDTO productImgDTO = ProductImgDTO.entityToDto(image);
            productImgDTOList.add(productImgDTO);
        }

        Product product = adminProductRepository.findById(id).orElseThrow(EntityExistsException::new);
        ProductDTO productDTO = ProductDTO.entityToDto(product);
        productDTO.setProductImgDTOList(productImgDTOList);

        return productDTO;
    }


    public PageResultDTO<ProductDTO, Product> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<Product> result = adminProductRepository.findAll(pageable);
        Function<Product, ProductDTO> fn = (entity -> ProductDTO.entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    };

    public void delete(Long id) {
        adminProductRepository.delete(adminProductRepository.findById(id).get());
    }

    // 소영 main page - random product
    public List<Product> getRandomProduct() {
        List<Product> allProducts = adminProductRepository.findAll();
        List<Product> randomProducts = new ArrayList<>(allProducts);
        Collections.shuffle(randomProducts);
        return randomProducts.subList(0, 5);
    }
}