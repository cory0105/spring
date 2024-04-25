package dw.wholesale_company.service;

import dw.wholesale_company.exception.ResourceNotFoundException;
import dw.wholesale_company.model.Employee;
import dw.wholesale_company.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    // 3. 사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보
    public Employee getEmployeeLast(){
        List<Employee> employeeList = employeeRepository.findByposition("사원");
        if (employeeList.isEmpty()){
            throw new ResourceNotFoundException("Employee", "position", "사원");
        }
        Employee result = employeeList.getFirst();
        for (int i=1; i<employeeList.size(); i++){
            if (employeeList.get(i).getHireDate().compareTo(result.getHireDate())>0){
                result = employeeList.get(i);
            }
        }
        return result;
    }
    public Employee getEmployeeLastJpql(){
        return employeeRepository.getEmployeeLastJpql();
    }

    public Employee getEmployeeByHireLatest() {
        return employeeRepository.findAll()
                .stream().filter(e->e.getPosition().equals("사원"))
                .sorted(Comparator.comparing(Employee::getHireDate).reversed())
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Employee", "error", "error"));
    }
}
