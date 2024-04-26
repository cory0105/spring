package dw.wholesale_company.service;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.EmployeeRepository;
import dw.wholesale_company.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {
    EmployeeRepository employeeRepository;
    OrderRepository orderRepository;

    public TestService(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
    }

    //1. 도시이름(city)을 매개변수로 받아 그 도시출신의 사원 정보를 보이시오.
    public List<Employee> getEmployeeByCity(String city) {
        return employeeRepository.findAll().stream().filter(e->e.getCity().equals(city)).collect(Collectors.toList());
    }

    //2. 주문번호를 매개변수(orderId)로 받아 주문한 고객의 정보를 보이시오.
    public Customer getCustomerByOrderId(String orderId) {
        return orderRepository.findById(orderId).get().getCustomer();
    }

    //3. 주문년도(orderYear)를 매개변수로 받아 그 해의 주문건수(int)를 보이시오.
    public int getOrderNumByOrderYear(int orderYear) {
        return orderRepository.findAll().stream().filter(o->o.getOrderDate().getYear() == orderYear).collect(Collectors.toList()).size();
    }

    //4. 직위(position)와 나이대(year)를 매개변수로 받아 해당정보에 맞는 사원들의 정보를 보이시오.
    // 예를 들어 20대는 매개변수 year=20 이고 나이가 20살이상 30살미만을 의미함.
    // 나이계산은 (올해 - 태어난해)로 계산.
    public List<Employee> getEmployeeByPositionAndYear(String position, int year) {
        return employeeRepository.findAll().stream()
                .filter((Employee e)->e.getPosition().equals(position)
                        && Year.now().getValue() - e.getBirthDate().getYear() >= year
                        && Year.now().getValue() - e.getBirthDate().getYear() < year+10)
                .collect(Collectors.toList());
    }
}
