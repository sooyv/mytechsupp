package techsuppDev.techsupp.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.apache.coyote.http11.Constants.a;

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
        return productRepository.findOne(productId);
    }

//    findone 5번 돌려서 가져오는 걸로 해보자
    public List<Product> findFiveProduct(int orderNumber, String keyword) {
        return productRepository.findFiveProduct(orderNumber, keyword);
    }
//보통 컨트롤러에서 구현
//    근데 그냥 되면 굳이 바꿀 필요는 없음
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

//    db에 있는 상품 row 갯수 구하는 것
//    페이징을 위해서 필요
//    public Object getNumberOfProduct(int limitPageNumber) {
//        return productRepository.getRow(limitPageNumber);
//    }

//    public Object getNumberOfProduct() {
//        return productRepository.getRow();
//    }
    public Object getNumberOfProduct(int pagingNumber, String keyword) {
    return productRepository.ProductCount(pagingNumber, keyword);
    }




//    테스트 데이터 자바로 생성 (100)개

    public void createtestdataproduct() {
        productRepository.insertTestData();
        System.out.println("========");
        System.out.println("service");
    }

}
