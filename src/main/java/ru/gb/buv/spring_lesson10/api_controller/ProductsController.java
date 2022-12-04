
package ru.gb.buv.spring_lesson10.api_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.buv.spring_lesson10.dto.ProductDto;
import ru.gb.buv.spring_lesson10.entity.Product;
import ru.gb.buv.spring_lesson10.service.ProductService;

@RestController
@RequestMapping("/api/v1/products") // http://localhost:8080/myapp - отправная точка (index.html)!!!
public class ProductsController {
    @Autowired
    ProductService productService;

    //**************************
    //Основной метод по возвращению фильтрованного списка dto сущностей!
    @GetMapping()
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Long minCost,
            @RequestParam(name = "max_cost", required = false) Long maxCost,
            @RequestParam(name = "title_part", required = false) String titlePart
    ){
        if(page < 1){page = 1;}
        //отмапим страницу студентов в страницу ДТО
        return productService.find(minCost,maxCost,titlePart,page).map(
                s -> new ProductDto(s)
        );
    }
    //**************************

    //Получение сущности по id
    @GetMapping("/{id}")
    public ProductDto getProductDtoById(@PathVariable Long id){
        return new ProductDto(productService.getProductById(id));
        /*throw new UnsupportedOperationException();*/
    }
    //Добавление
    @PostMapping()
    public Page<ProductDto> saveNewProduct(@RequestBody Product newProduct){
        newProduct.setId(null);
        productService.saveProduct(newProduct);
        return getAllProducts(1, null,null, null);
    }
    //Удаление
    //список самообновляется location.reload(); на html после удаления!!!!
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

}
