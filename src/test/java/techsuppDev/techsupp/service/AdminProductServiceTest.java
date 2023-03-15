package techsuppDev.techsupp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import techsuppDev.techsupp.DTO.PageRequestDTO;
import techsuppDev.techsupp.DTO.PageResultDTO;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.domain.Product;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminProductServiceTest {

    @Autowired
    private AdminProductService adminProductService;

    @Test
    public void register() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            ProductDTO productDTO = ProductDTO.builder()
                    .productName("상품입니다.")
                    .totalPrice(200000)
                    .period(LocalDate.now())
                    .information("안녕하세요.")
                    .investPrice(10000)
                    .build();
//            System.out.println(adminProductService.register(productDTO));
        });

    }

    @Test
    public void List() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<ProductDTO, Product> pageResultDTO = adminProductService.getList(pageRequestDTO);

//        for (ProductDTO productDTO : pageResultDTO.getDtoList()) {
//            System.out.println(productDTO);
//        }
        System.out.println(pageResultDTO);
    }

}