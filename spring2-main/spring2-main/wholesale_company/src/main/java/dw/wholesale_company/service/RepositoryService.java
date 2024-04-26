package dw.wholesale_company.service;

import dw.wholesale_company.repository.*;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {
    CustomerRepository customerRepository;
    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    MileageRepository mileageRepository;
    OrderDetailRepository orderDetailRepository;
    OrderRepository orderRepository;
    ProductRepository productRepository;

    public RepositoryService(CustomerRepository customerRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, MileageRepository mileageRepository, OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.mileageRepository = mileageRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
}
