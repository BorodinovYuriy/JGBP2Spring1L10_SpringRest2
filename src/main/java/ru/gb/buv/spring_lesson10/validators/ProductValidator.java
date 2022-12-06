package ru.gb.buv.spring_lesson10.validators;

import org.springframework.stereotype.Component;
import ru.gb.buv.spring_lesson10.dto.ProductDto;
import ru.gb.buv.spring_lesson10.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if(productDto.getCost() < 0){
            errors.add("Цена продукта не может быть отрицательной");
        }
        if(productDto.getTitle().isBlank()){
            errors.add("Продукт должен иметь название");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
