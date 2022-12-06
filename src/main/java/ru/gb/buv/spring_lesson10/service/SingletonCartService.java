package ru.gb.buv.spring_lesson10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.buv.spring_lesson10.dto.ProductDto;
import ru.gb.buv.spring_lesson10.singleton_cart.SingletonCart;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SingletonCartService {
    private final SingletonCart singletonCart;


    //SingletonCart
    public List<ProductDto> getCartList(){
        return singletonCart.getCartList();
    }
    public void addDtoToSingletonCart(ProductDto productDto){
        singletonCart.addDtoToCart(productDto);
    }
    public void deleteDtoFromSingletonCart(Long id){
        singletonCart.deleteDtoFromCartById(id);
    }
}
