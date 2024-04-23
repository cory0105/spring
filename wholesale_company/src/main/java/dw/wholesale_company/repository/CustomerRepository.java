package dw.wholesale_company.repository;

import dw.wholesale_company.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    // 4. 고객 전체의 평균마일리지보다 평균마일리지가 큰 고객 정보
    @Query("select c1 from Customer c1 where mileage > (select avg(mileage) from Customer c2)")
    List<Customer> getCustomerAvgBigJpql();
}
