package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // 제품중에서 제품명에 어떤한 키워드가 들어간 제품을 모두 찾기
    public List<Product> getProductByKeywordLambda(String keyword){
        return productRepository.findAll().stream()
                .filter(p->p.getProductName().contains(keyword)).collect(Collectors.toList());
    }
    public List<Product> getProductByKeywordJpql(String keyword){
        return productRepository.getProductByKeywordJpql(keyword);
    }
    public List<Product> getProductByKeyword(String keyword){
        List<Product> productList = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            if(productList.get(i).getProductName().contains(keyword)){
                result.add(productList.get(i));
            }
        }
        return result;
    }

    // 제품 단가가 5000원 이상 10000원 이하인 제품에는 무엇이 있는지 검색
    public List<Product> getProductByPriceLambda(int lowLimit, int highLimit){
        return productRepository.findAll().stream().filter(p->p.getUnitPrice() >= lowLimit && p.getUnitPrice() <= highLimit).collect(Collectors.toList());
    }
    public List<Product> getProductByPriceJpql(int lowLimit, int highLimit){
        return productRepository.getProductByPriceJpql(lowLimit, highLimit);
    }
    public List<Product> getProductByPrice(int lowLimit, int highLimit){
        List<Product> productList = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            if (productList.get(i).getUnitPrice()>=lowLimit && productList.get(i).getUnitPrice()<=highLimit){
                result.add(productList.get(i));
            }
        }
        return result;
    }
    public List<Product> getProductByproductIds(List<Long> nums){
        List<Product> productList = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        for (int i=0; i<productList.size(); i++){
            for (int j=0; j<nums.size(); j++){
                if (productList.get(i).getProductId()==nums.get(j)){
                    result.add(productList.get(i));
                }
            }
        }
        return result;
    }
    public List<Product> getProductByproductIdsLambda(List<Long> nums){
        return productRepository.findAllById(nums);
    }

    public List<Product> teacher(List<Long> nums){
        return productRepository.findAll().stream().filter(product -> nums.contains(product.getProductId()))
                .collect(Collectors.toList());
    }
    // 제품 재고금액이 높은 상위10개 제품
    public List<Product> getProductByInventory(int top){
        return productRepository.findAll().stream().sorted(Comparator.comparingInt((Product p)->p.getInventory()*p.getUnitPrice()).reversed())
                .limit(top).collect(Collectors.toList());
    }
}