package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrder(){
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    // 1. 주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    @GetMapping("/exam/1")
    public ResponseEntity<List<Order>> getOrderDate20210501(){
        return new ResponseEntity<>(orderService.getOrderDate20210501(), HttpStatus.OK);
    }
    @GetMapping("/teacher/1/{date}")
    public ResponseEntity<List<Order>> getOrderByDateAfter(@PathVariable LocalDate date) {
        return new ResponseEntity<>(orderService.getOrderByDateAfter(date), HttpStatus.OK);
    }
    @GetMapping("/order/customer/{date}")
    public ResponseEntity<List<Customer>> getCustomerByOrderDate(@PathVariable LocalDate date){
        return new ResponseEntity<>(orderService.getCustomerByOrderDate(date), HttpStatus.OK);
    }
    @GetMapping("/order/test")
    public List<Order> test(@RequestBody List<Customer> customers){
        return orderService.test(customers);
    }
}
