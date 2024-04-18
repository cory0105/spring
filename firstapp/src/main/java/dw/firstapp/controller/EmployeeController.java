package dw.firstapp.controller;

import dw.firstapp.model.Employee;
import dw.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    // 의존성주입
    EmployeeService employeeService;
    // 매개변수를 사용한 생성자
    @Autowired // 생성자 주입(권장하는 방법)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/api/Employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        // Service
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/api/Employee")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/api/Employee/{id}")
    public Employee getEmployeeById(@PathVariable long id){
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("/api/Employee/{id}")
    public Employee updateEmployeeById(@PathVariable long id, @RequestBody Employee employee){
        return employeeService.updateEmployeeById(id, employee);
    }
    @DeleteMapping("/api/Employee/{id}")
    public Employee deleteEmployeeById(@PathVariable long id){
        return employeeService.deleteEmployeeById(id);
    }
}
