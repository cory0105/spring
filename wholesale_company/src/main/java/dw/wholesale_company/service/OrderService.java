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

    //도시별로 주문금액합을 보이되 주문금액합이 많은 상위 5개의 도시에 대한 결과만 보이시오.
//    @Autowired
//    EntityManager entityManager;
    public List<Object[]> getTopCitiesByTotalOrderAmount(int limit) {
        List<Object[]> cities = orderRepository.getTopCitiesByTotalOrderAmount();
        return cities.stream().limit(limit).collect(Collectors.toList());
//        return entityManager.createQuery(
//                        "SELECT c.city, SUM(od.orderQuantity * od.unitPrice) " +
//                                "FROM OrderDetail od " +
//                                "JOIN od.order o " +
//                                "JOIN o.customer c " +
//                                "GROUP BY c.city " +
//                                "ORDER BY SUM(od.orderQuantity * od.unitPrice) DESC")
//                .setMaxResults(limit)
//                .getResultList();
    }

    //‘서울특별시’ 고객들에 대해 주문년도별 주문건수를 보이시오
    public List<Object[]> getOrderCountByYearForCity(String city) {
        return orderRepository.getOrderCountByYearForCity(city);
    }
}
