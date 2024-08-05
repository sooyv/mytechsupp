package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.controller.form.FeedbackProductListForm;
import techsuppDev.techsupp.controller.form.ProductListForm;
import techsuppDev.techsupp.controller.form.ProductListNoWishForm;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    public final ProductRepository productRepository;

    public Object findOneProduct(Long productId, String userId) {
        return productRepository.findOneProduct(productId, userId);
    }

    // five product (login 했을 경우)
    public List<ProductListForm> findFiveProductOnLogin(int orderNumber, String keyword, String userId) {
        return productRepository.findFiveProduct(orderNumber, keyword, userId);
    }
    // five product (login 안했을 경우)
    public List<ProductListNoWishForm> findFiveProductOnNoLogin(int orderNumber, String keyword, String userId) {
        return productRepository.findFiveProductNoWish(orderNumber, keyword, userId);
    }

    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    public Object getNumberOfProduct(int pagingNumber, String keyword) {
    return productRepository.ProductCount(pagingNumber, keyword);
    }





//    feedback 으로 보내주는 service
    public List<FeedbackProductListForm> findFiveProductFeedback(int orderNumber, String keyword) {
        return productRepository.findFiveProductFeedback(orderNumber, keyword);
    }

//    feedback paging service

    public Object getNumberOfFeedback(int pagingNumber, String keyword) {
        return productRepository.FeedbackCount(pagingNumber, keyword);
    }














//    테스트 데이터 자바로 생성 (100)개

    public void createtestdataproduct() {
        productRepository.insertTestData();

        productRepository.createinvesttestdata();

        System.out.println("========");
        System.out.println("service");
    }

}
