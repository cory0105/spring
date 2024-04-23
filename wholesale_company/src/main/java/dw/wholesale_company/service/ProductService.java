package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    // 2. 제품의 재고가 50개 미만인 제품 정보 얻기
    public List<Product> getProduct50(){
        List<Product> productList = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            if (productList.get(i).getInventory() < 50){
                result.add(productList.get(i));
            }
        }
        return result;
    }
    public List<Product> getProduct50Jpql(){
        return productRepository.getProduct50Jpql();
    }

    public List<Product> getProductByInventoryUnder(int num) {
        return productRepository.findAll()
                .stream().filter(p->p.getInventory()<num)
                .collect(Collectors.toList());
    }
}