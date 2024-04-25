package dw.wholesale_company.controller;

import dw.wholesale_company.model.Employee;
import dw.wholesale_company.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    // 3. 사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보
    @GetMapping("/exam/3")
    public ResponseEntity<Employee> getEmployeeLast(){
        return new ResponseEntity<>(employeeService.getEmployeeLast(), HttpStatus.OK);
    }
    @GetMapping("/jpql/3")
    public ResponseEntity<Employee> getEmployeeLastJpql(){
        return new ResponseEntity<>(employeeService.getEmployeeLastJpql(), HttpStatus.OK);
    }
    @GetMapping("/teacher/3")
    public ResponseEntity<Employee> getEmployeeByHireLatest() {
        return new ResponseEntity<>(employeeService.getEmployeeByHireLatest(), HttpStatus.OK);
    }
}
