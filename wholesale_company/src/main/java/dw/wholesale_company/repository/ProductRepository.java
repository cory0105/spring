package dw.wholesale_company.repository;

import dw.wholesale_company.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 2. 제품의 재고가 50개 미만인 제품 정보 얻기
    @Query("select p1 from Product p1 where inventory < 50")
    List<Product> getProduct50Jpql();
}
