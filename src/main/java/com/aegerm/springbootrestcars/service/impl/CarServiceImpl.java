package com.aegerm.springbootrestcars.service.impl;

import com.aegerm.springbootrestcars.domain.Car;
import com.aegerm.springbootrestcars.domain.dto.CarDTO;
import com.aegerm.springbootrestcars.repository.CarRepository;
import com.aegerm.springbootrestcars.service.CarService;
import com.aegerm.springbootrestcars.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    @Override
    public List<CarDTO> findAll() {
        List<CarDTO> cars = this.repository.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
        return cars;
    }

    @Override
    public CarDTO findById(Long id) {
        Optional<Car> car = this.repository.findById(id);
        return car.map(CarDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado!"));
    }

    @Override
    public List<CarDTO> findByName(String name) {
        List<CarDTO> cars = this.repository.findCarByName(name).stream().map(CarDTO::create).collect(Collectors.toList());
        return cars;
    }

    @Override
    public CarDTO insert(Car car) {
        Assert.isNull(car.getId(), "Não foi possível inserir o registro!");
        return CarDTO.create(this.repository.save(car));
    }

    @Override
    public CarDTO update(Long id, Car car) {
        return this.repository.findById(id).map(_car -> {
            _car.setName(car.getName());
            _car.setType(car.getType());

            Car updated = this.repository.save(_car);
            return CarDTO.create(updated);
        }).orElseThrow(() -> new RuntimeException("Não foi possível atualizar o registro!"));
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
