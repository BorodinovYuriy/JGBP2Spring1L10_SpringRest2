
package ru.gb.buv.spring_lesson10.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ru.gb.buv.spring_lesson10.entity.Product;

@Repository//-не обязательная, но наглядненько
public interface ProductRepository extends JpaRepository<Product, Long>
        , JpaSpecificationExecutor<Product> {

}
