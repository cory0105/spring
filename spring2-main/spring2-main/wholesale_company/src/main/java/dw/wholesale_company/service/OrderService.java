package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    // 1. 주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    public List<Order> getOrderDate20210501(){
        List<Order> orderList = orderRepository.findAll();
        List<Order> result = new ArrayList<>();
        for (int i=0; i<orderList.size(); i++){
            if (orderList.get(i).getOrderDate().compareTo(LocalDate.of(2021,5,1).atStartOfDay()) > 0){
                result.add(orderList.get(i));
            }
        }
        return result;
    }

    public List<Order> getOrderByDateAfter(LocalDate date) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(a -> a.getOrderDate().compareTo(date.atStartOfDay()) > 0)
                .collect(Collectors.toList());
    }

    // 특정 날짜에 주문한 고객의 모든 정보
    public List<Customer> getCustomerByOrderDate(LocalDate date) {
        return orderRepository.findAll().stream().filter(a -> a.getOrderDate().toLocalDate().equals(date))
                .map(Order::getCustomer).collect(Collectors.toList());
    }

    public List<Order> test(List<Customer> customers){
        return orderRepository.findAllByCustomerIn(customers);
    }
}
