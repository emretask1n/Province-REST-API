package com.emretaskin.provinceservice.repository;


import com.emretaskin.provinceservice.model.Province;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends MongoRepository<Province, String> {
    List<Province> findAllByName(String name);
    Optional<Province> findByName(String name);
}
