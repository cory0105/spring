package dw.firstapp.controller;

import dw.firstapp.model.Employee;
import dw.firstapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        // Service
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
    }
    @GetMapping("/api/Employee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @GetMapping("/api/Employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
    @PutMapping("/api/Employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployeeById(id, employee), HttpStatus.OK);
    }
    @DeleteMapping("/api/Employee/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable long id){
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
}
