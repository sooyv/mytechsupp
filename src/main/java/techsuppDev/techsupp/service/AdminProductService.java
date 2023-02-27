package techsuppDev.techsupp.service;

import techsuppDev.techsupp.DTO.PageRequestDTO;
import techsuppDev.techsupp.DTO.PageResultDTO;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.domain.Product;

public interface AdminProductService {
    Long register(ProductDTO productDTO);

    PageResultDTO<ProductDTO, Product> getList(PageRequestDTO pageRequestDTO);

    default Product dtoToEntity(ProductDTO productDTO) {
        Product entity = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getProductName())
                .totalPrice(productDTO.getTotalPrice())
                .period(productDTO.getPeriod())
                .information(productDTO.getInformation())
                .investPrice(productDTO.getInvestPrice()).build();
        return entity;
    }

    default ProductDTO entityToDto(Product entity) {
        ProductDTO dto = ProductDTO.builder()
                .id(entity.getId())
                .productName(entity.getName())
                .totalPrice(entity.getTotalPrice())
                .period(entity.getPeriod())
                .information(entity.getInformation())
                .productStatus(entity.getStatus())
                .investPrice(entity.getInvestPrice())
                .clickCount(entity.getClickCount()).build();
        return dto;
    }
}
