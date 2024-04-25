package dw.wholesale_company.repository;

import dw.wholesale_company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByposition(String position);

    // 3. 사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보
    @Query("select e1 from Employee e1 where position = '사원' and hireDate = (select max(hireDate) from Employee e2)")
    Employee getEmployeeLastJpql();
}
