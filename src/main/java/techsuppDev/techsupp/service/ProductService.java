package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.controller.form.ProductListForm;
import techsuppDev.techsupp.controller.form.ProductListNoWishForm;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.ProductRepository;

import java.util.List;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class ProductService {
    public final ProductRepository productRepository;

//    public class paging;
//    {
//            int allCount;
//            int pageCount;
//    };

//    Object paging(Object allCount, Object pageCount) {
//            this.allCount = allCount.getClass();
//            this.pageCount = pageCount;
//    };



//    일단 하나만 검색해서 보내주기
//    보내주기 성공
//    나중에 매개 변수만 바꿔주면 로직 짜는 거는 어렵지 않을
    public Object findOneProduct(Long productId) {
        return productRepository.findOneProduct(productId);
    }

//    five product (login 했을 경우)
    public List<ProductListForm> findFiveProductOnLogin(int orderNumber, String keyword, String userId) {
        return productRepository.findFiveProduct(orderNumber, keyword, userId);
    }
//    five product (login 안했을 경우)
    public List<ProductListNoWishForm> findFiveProductOnNoLogin(int orderNumber, String keyword, String userId) {
        return productRepository.findFiveProductNoWish(orderNumber, keyword, userId);
    }


//보통 컨트롤러에서 구현
//    근데 그냥 되면 굳이 바꿀 필요는 없음
    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    public Object getNumberOfProduct(int pagingNumber, String keyword) {
    return productRepository.ProductCount(pagingNumber, keyword);
    }





//    feedback 으로 보내주는 service
    public List<ProductListForm> findFiveProductFeedback(int orderNumber, String keyword) {
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
