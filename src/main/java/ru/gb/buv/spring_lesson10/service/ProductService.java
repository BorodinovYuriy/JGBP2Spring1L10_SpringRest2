
package ru.gb.buv.spring_lesson10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.buv.spring_lesson10.repository.ProductRepository;
import ru.gb.buv.spring_lesson10.entity.Product;
import ru.gb.buv.spring_lesson10.repository.specification.ProductSpecification;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //*****************************************
    //find Page method!!!
    public Page<Product> find(Long minCost, Long maxCost, String titlePart, Integer page){
        Specification<Product> spec = Specification.where(null);
        if(minCost != null){
            spec = spec.and(ProductSpecification.costGreaterOrEqualsThan(minCost));
        }
        if(maxCost != null){
            spec = spec.and(ProductSpecification.costLessOrEqualsThan(maxCost));
        }
        if(titlePart != null){
            spec = spec.and(ProductSpecification.nameLike(titlePart));
        }
        return (productRepository.findAll(spec,PageRequest.of(page -1,5)))/*.stream().toList()*/;
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product createProduct(String title, Long cost,String secret_field){
        Product product = new Product();
        product.setTitle(title);
        product.setCost(cost);
        product.setSecret_field(secret_field);
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    public Long getCount() {
        return productRepository.count();
    }

    public Page<Product> getPage(Integer offset, Integer limit) {
        return productRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id")));

    }

    //*********Код начальной инициализации***********
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    private void fillDataBaseAfterStartApplication(){
        for (int i = 0; i < 100; i++) {
            productRepository.save(createProduct("prod-"+i,(100L+i+i),"secret_field"));
        }
    }
    //Save method
    public void saveProduct(Product product){
        productRepository.save(product);
    }
}
