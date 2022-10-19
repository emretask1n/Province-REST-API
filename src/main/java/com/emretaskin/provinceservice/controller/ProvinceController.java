package com.emretaskin.provinceservice.controller;

import com.emretaskin.provinceservice.exception.ProvinceAlreadyExistException;
import com.emretaskin.provinceservice.exception.ProvinceNotFoundException;
import com.emretaskin.provinceservice.model.Province;
import com.emretaskin.provinceservice.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/provinces")
@AllArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<Province>> getProvinces(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(provinceService.getProvinces(name), OK);
    }

    @GetMapping("/{provinceId}")
    public ResponseEntity<Province> getProvince(@PathVariable String provinceId){
        return new ResponseEntity<>(getProvinceById(provinceId),OK);
    }

    @PostMapping
    public ResponseEntity<Province> createProvince(@RequestBody Province newProvince) {
        return new ResponseEntity<>(provinceService.createProvince(newProvince), CREATED);
    }

    @PutMapping("/{provinceId}")
    public ResponseEntity<Void> getProvince(@PathVariable String provinceId, @RequestBody Province newProvince) {
        provinceService.updateProvince(provinceId,newProvince);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{provinceId}")
    public ResponseEntity<Void> deleteProvince(@PathVariable String provinceId){
        provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(OK);
    }

    private Province getProvinceById(String provinceId) {
        return provinceService.getProvinceById(provinceId);
    }

    @ExceptionHandler(ProvinceNotFoundException.class)
    public ResponseEntity<String> handleProvinceNotFoundException(ProvinceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    
    @ExceptionHandler(ProvinceAlreadyExistException.class)
    public ResponseEntity<String> handleProvinceAlreadyExistException(ProvinceAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
