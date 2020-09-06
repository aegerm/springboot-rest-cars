package com.aegerm.springbootrestcars.service;

import com.aegerm.springbootrestcars.domain.Car;
import com.aegerm.springbootrestcars.domain.dto.CarDTO;

import java.util.List;

public interface CarService {

    List<CarDTO> findAll();

    CarDTO findById(Long id);

    List<CarDTO> findByName(String name);

    CarDTO insert(Car car);

    CarDTO update(Long id, Car car);

    void delete(Long id);
}
