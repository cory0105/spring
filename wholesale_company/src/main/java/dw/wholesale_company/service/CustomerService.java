package dw.wholesale_company.service;

import dw.wholesale_company.controller.MileageController;
import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.repository.CustomerRepository;
import dw.wholesale_company.repository.MileageRepository;
import io.micrometer.observation.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    // 4. 고객 전체의 평균마일리지보다 평균마일리지가 큰 고객 정보
    public List<Customer> getCustomerAvgBig(){
        List<Customer> customerList = customerRepository.findAll();
        List<Customer> result = new ArrayList<>();
        int avg = 0;
        for (int i=0; i<customerList.size(); i++){
            avg = avg + customerList.get(i).getMileage();
        }
        for (int i=0; i<customerList.size(); i++){
            if (avg/customerList.size() < customerList.get(i).getMileage()){
                result.add(customerList.get(i));
            }
        }
        return result;
    }
    public List<Customer> getCustomerAvgBigJpql(){
        return customerRepository.getCustomerAvgBigJpql();
    }
    public List<Customer> getCustomerWithHighMileThanAvg() {
        List<Customer> customers = customerRepository.findAll();
        int sum = 0;
        for (int i=0; i<customers.size(); i++) {
            sum = sum + customers.get(i).getMileage();
        }
        Double avg = (double)sum / (double)customers.size();
        return customers.stream().filter(c->c.getMileage() > avg).collect(Collectors.toList());
    }

    // 마일리지 등급명에 따른 고객수를 보이시오.
    @Autowired
    MileageRepository mileageRepository;
    public int getCustomerByMileageGrade(String grade){
        return customerRepository.findAll().stream()
                .filter((Customer c) -> c.getMileage() >= mileageRepository.findById(grade).get().getLowLimit()
                        && c.getMileage() <= mileageRepository.findById(grade).get().getHighLimit()).toList().size();
    }
}
