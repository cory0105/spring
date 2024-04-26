package dw.wholesale_company.repository;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByCustomerIn(List<Customer> customers);

    @Query("SELECT c.city, SUM(od.orderQuantity * od.unitPrice) " +
            "FROM OrderDetail od " +
            "JOIN od.order o " +
            "JOIN o.customer c " +
            "GROUP BY c.city " +
            "ORDER BY SUM(od.orderQuantity * od.unitPrice) DESC")
    List<Object[]> getTopCitiesByTotalOrderAmount();

    @Query("SELECT YEAR(o.orderDate), COUNT(o)" +
            "FROM Order o " +
            "WHERE o.customer.customerId IN ( " +
            "   SELECT c.customerId " +
            "   FROM Customer c " +
            "   WHERE c.city = :city " +
            ") " +
            "GROUP BY YEAR(o.orderDate)")
    List<Object[]> getOrderCountByYearForCity(@Param("city") String city);
}
