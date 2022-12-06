package ru.gb.buv.spring_lesson10.converters;

import org.springframework.stereotype.Component;
import ru.gb.buv.spring_lesson10.dto.ProductDto;
import ru.gb.buv.spring_lesson10.entity.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId()
                , productDto.getTitle()
                , productDto.getCost());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId()
                , product.getTitle()
                , product.getCost());
    }
}
