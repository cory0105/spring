package dw.wholesale_company.controller;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/product/lambda/{keyword}")
    public ResponseEntity<List<Product>> getProductByKeywordLambda(@PathVariable String keyword){
        return new ResponseEntity<>(productService.getProductByKeywordLambda(keyword), HttpStatus.OK);
    }
    @GetMapping("/product/jpql/{keyword}")
    public ResponseEntity<List<Product>> getProductByKeywordJpql(@PathVariable String keyword){
        return new ResponseEntity<>(productService.getProductByKeywordJpql(keyword), HttpStatus.OK);
    }
    @GetMapping("/product/{keyword}")
    public ResponseEntity<List<Product>> getProductByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(productService.getProductByKeyword(keyword), HttpStatus.OK);
    }
    @GetMapping("/product/lambda/{lowLimit}/{highLimit}")
    public ResponseEntity<List<Product>> getProductByPriceLambda(@PathVariable int lowLimit, @PathVariable int highLimit){
        return new ResponseEntity<>(productService.getProductByPriceLambda(lowLimit, highLimit), HttpStatus.OK);
    }
    @GetMapping("/product/jpql/{lowLimit}/{highLimit}")
    public ResponseEntity<List<Product>> getProductByPriceJpql(@PathVariable int lowLimit, @PathVariable int highLimit){
        return new ResponseEntity<>(productService.getProductByPriceJpql(lowLimit, highLimit), HttpStatus.OK);
    }
    @GetMapping("/product/price")
    public ResponseEntity<List<Product>> getProductByPrice(@RequestParam int low, @RequestParam int high){
        return new ResponseEntity<>(productService.getProductByPrice(low, high), HttpStatus.OK);
    }
    @GetMapping("/product/productIds")
    public ResponseEntity<List<Product>> getProductByproductIds(@RequestBody List<Long> nums){
        return new ResponseEntity<>(productService.getProductByproductIds(nums), HttpStatus.OK);
    }
    @GetMapping("/product/lambda/productIds")
    public ResponseEntity<List<Product>> getProductByproductIdsLambda(@RequestBody List<Long> nums){
        return new ResponseEntity<>(productService.getProductByproductIdsLambda(nums), HttpStatus.OK);
    }
    @GetMapping("/product/top/{top}")
    public ResponseEntity<List<Product>> getProductByInventory(@PathVariable int top){
        return new ResponseEntity<>(productService.getProductByInventory(top), HttpStatus.OK);
    }
}
