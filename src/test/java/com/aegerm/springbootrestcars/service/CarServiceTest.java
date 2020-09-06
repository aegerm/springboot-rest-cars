package com.aegerm.springbootrestcars.service;

import com.aegerm.springbootrestcars.domain.Car;
import com.aegerm.springbootrestcars.domain.dto.CarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Test
    public void insertCarTest() {
        Car car = new Car();
        car.setName("Carro 1");
        car.setType("Tipo 1");

        CarDTO dto = this.carService.insert(car);
        assertNotNull(dto);

        Long id = dto.getId();
        assertNotNull(id);

        Optional<CarDTO> optional = this.carService.findById(id);
        assertTrue(optional.isPresent());

        this.carService.delete(id);
        assertFalse(this.carService.findById(id).isPresent());
    }

    @Test
    public void findAllTest() {
        List<CarDTO> cars = this.carService.findAll();
        assertFalse(cars.isEmpty());
        assertEquals(10, cars.size());
    }

    @Test
    public void findByIdTest() {
        Optional<CarDTO> dto = this.carService.findById(5L);
        assertNotNull(dto.get());
        assertEquals("Carro 5", dto.get().getName());
    }

    @Test
    public void findByNameTest() {
        List<CarDTO> cars = this.carService.findByName("Carro 8");
        assertFalse(cars.isEmpty());
    }

    @Test
    public void updateCarTest() {
        Car updated = new Car();
        updated.setName("Carro 2 - UPDATE");

        this.carService.update(2L, updated);
        assertEquals(updated.getName(), this.carService.findById(2L).get().getName());
    }

    @Test
    public void deleteCarTest() {
        Optional<CarDTO> dto = this.carService.findById(10L);
        assertNotNull(dto);

        Long id = dto.get().getId();
        assertNotNull(id);

        this.carService.delete(id);
        assertFalse(this.carService.findById(id).isPresent());
    }
}
