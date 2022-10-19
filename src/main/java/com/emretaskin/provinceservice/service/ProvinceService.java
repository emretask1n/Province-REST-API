package com.emretaskin.provinceservice.service;

import com.emretaskin.provinceservice.exception.ProvinceAlreadyExistException;
import com.emretaskin.provinceservice.exception.ProvinceNotFoundException;
import com.emretaskin.provinceservice.model.Province;
import com.emretaskin.provinceservice.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public List<Province> getProvinces(String name) {

        if (name==null) {
            return provinceRepository.findAll();
        } else {
            return provinceRepository.findAllByName(name);
        }
    }

    public Province createProvince(Province newProvince) {
        Optional<Province> provinceByName = provinceRepository.findByName(newProvince.getName());
        if (provinceByName.isPresent()) {
            throw new ProvinceAlreadyExistException("Province" + newProvince.getName() + "already exist!");
        }
        return provinceRepository.save(newProvince);
    }

    public void deleteProvince(String provinceId) {
        provinceRepository.deleteById(provinceId);
    }

    public Province getProvinceById(String provinceId) {
        return provinceRepository.findById(provinceId)
                .orElseThrow(() -> new ProvinceNotFoundException("Province not found with id:"+ provinceId));
    }

    public void updateProvince(String provinceId, Province newProvince) {
        Province oldProvince = getProvinceById(provinceId);
        oldProvince.setName(newProvince.getName());
        provinceRepository.save(oldProvince);
    }
}
