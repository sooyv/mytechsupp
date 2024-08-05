package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
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
import techsuppDev.techsupp.controller.form.AdminPaymentForm;
import techsuppDev.techsupp.domain.Image;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.AdminProductRepository;
import techsuppDev.techsupp.repository.PaymentRepository;
import techsuppDev.techsupp.repository.ProductImageRepository;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdminProductService {
    private final AdminProductRepository adminProductRepository;
    private final ProductImageService productImageService;
    private final ProductImageRepository productImageRepository;
    private final PaymentRepository paymentRepository;

    public Long productRegister(ProductDTO productDTO, List<MultipartFile> multipartFileList) throws Exception {
        Product product = productDTO.dtoToEntity(productDTO);
        adminProductRepository.save(product);

        for (int i = 0, max = multipartFileList.size(); i < max; i++) {
            Image image = null;

            if (productDTO.getProductImgDTOList().size() != 0){
                image = Image.builder()
                        .product(product)
                        .repImg(i == 0 ? "Y" : "N")
                        .imgId(productDTO.getProductImgDTOList().get(0).getId())
                        .build();
            } else{
                image = Image.builder()
                        .product(product)
                        .repImg(i == 0 ? "Y" : "N")
                        .build();
            }
            productImageService.saveImg(image, multipartFileList.get(i));
        }
        return product.getProductId();
    };

    @Transactional(readOnly = true)
    public ProductDTO getProductDetail(Long productId) {
        List<Image> imageList = productImageRepository.findByProductProductIdOrderByImgIdAsc(productId);
        List<ProductImgDTO> productImgDTOList = new ArrayList<>();

        for (Image image : imageList) {
            ProductImgDTO productImgDTO = ProductImgDTO.entityToDto(image);
            productImgDTOList.add(productImgDTO);
        }

        Product product = adminProductRepository.findById(productId).orElseThrow(EntityExistsException::new);
        ProductDTO productDTO = ProductDTO.entityToDto(product);
        productDTO.setProductImgDTOList(productImgDTOList);

        return productDTO;
    }


    public PageResultDTO<ProductDTO, Product> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("productId").descending());
        Page<Product> result = adminProductRepository.findAll(pageable);
        Function<Product, ProductDTO> fn = (entity -> ProductDTO.entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    };

    public void delete(Long productId) {
        adminProductRepository.delete(adminProductRepository.findById(productId).get());
    }


    // 소영 main page - random product
    public List<ProductDTO> getRandomProduct() {
        List<Product> allProducts = adminProductRepository.findAll();

        List<Product> randomProducts = new ArrayList<>(allProducts);
        System.out.println("main random: " + randomProducts);

        Collections.shuffle(randomProducts);

        List<Product> productList;
        if (randomProducts.size() < 5) {
            productList = randomProducts.subList(0, randomProducts.size());
        } else {
            productList = randomProducts.subList(0, 5);
        }
        List<ProductDTO> pDTOList = new ArrayList<ProductDTO>();

        for (Product product : productList){
            ProductDTO productDTO = ProductDTO.entityToDto(product);
            List<Image> imageList = productImageRepository.findByProductProductIdOrderByImgIdAsc(product.getProductId());

            List<ProductImgDTO> productImgDTOList = new ArrayList<>();

            if (imageList.isEmpty()) {
                return null;
            } else {
                productImgDTOList.add(ProductImgDTO.entityToDto(imageList.get(0)));
            }
            productDTO.setProductImgDTOList(productImgDTOList);
            pDTOList.add(productDTO);
        }


        return pDTOList;
    }
//    public PageResultDTO<AdminPaymentForm, AdminPaymentForm> paymentList(PageRequestDTO pageRequestDTO) {
//        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
//        Page<AdminPaymentForm> result = paymentRepository.getAllPayment(pageable);
//        System.out.println(result);
//        Function<AdminPaymentForm, AdminPaymentForm> fn = (entity -> entity);
//
//        return new PageResultDTO<>(result, fn);
//    }
    public PageResultDTO<AdminPaymentForm, AdminPaymentForm> paymentList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("productId").descending());
        Page<AdminPaymentForm> result = paymentRepository.getAllPayment(pageable);
        System.out.println(result);
        Function<AdminPaymentForm, AdminPaymentForm> fn = (entity -> entity);

        return new PageResultDTO<>(result, fn);
    }

}