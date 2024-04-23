package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    // 4. 고객 전체의 평균마일리지보다 평균마일리지가 큰 고객 정보
    @GetMapping("/exam/4")
    public ResponseEntity<List<Customer>> getCustomerAvgBig(){
        return new ResponseEntity<>(customerService.getCustomerAvgBig(), HttpStatus.OK);
    }
    @GetMapping("/jpql/4")
    public ResponseEntity<List<Customer>> getCustomerAvgBigJpql(){
        return new ResponseEntity<>(customerService.getCustomerAvgBigJpql(), HttpStatus.OK);
    }
    @GetMapping("/teacher/4")
    public ResponseEntity<List<Customer>> getCustomerWithHighMileThanAvg() {
        return new ResponseEntity<>(customerService.getCustomerWithHighMileThanAvg(), HttpStatus.OK);
    }
}
