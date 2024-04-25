package dw.wholesale_company.service;

import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.repository.MileageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileageService {
    MileageRepository mileageRepository;

    public MileageService(MileageRepository mileageRepository) {
        this.mileageRepository = mileageRepository;
    }

    public List<Mileage> getAllMileage(){
        return mileageRepository.findAll();
    }
}
