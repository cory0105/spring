package dw.wholesale_company.controller;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    // 2. 제품의 재고가 50개 미만인 제품 정보 얻기
    @GetMapping("/exam/2")
    public ResponseEntity<List<Product>> getProduct50(){
        return new ResponseEntity<>(productService.getProduct50(), HttpStatus.OK);
    }
    @GetMapping("/jpql/2")
    public ResponseEntity<List<Product>> getProduct50Jpql(){
        return new ResponseEntity<>(productService.getProduct50Jpql(), HttpStatus.OK);
    }
    @GetMapping("/teacher/2/{num}")
    public ResponseEntity<List<Product>> getProductByInventoryUnder(@PathVariable int num) {
        return new ResponseEntity<>(productService.getProductByInventoryUnder(num), HttpStatus.OK);
    }
}
