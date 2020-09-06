package com.aegerm.springbootrestcars.service;

import com.aegerm.springbootrestcars.domain.Car;
import com.aegerm.springbootrestcars.domain.dto.CarDTO;
import com.aegerm.springbootrestcars.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

        dto = this.carService.findById(id);
        assertNotNull(dto);

        assertEquals(car.getName(), dto.getName());
        assertEquals(car.getType(), dto.getType());

        this.carService.delete(id);

        try {
            assertNull(this.carService.findById(id));
            fail("O carro não foi excluído!");
        } catch (ObjectNotFoundException exception) {}
    }

    @Test
    public void findAllTest() {
        List<CarDTO> cars = this.carService.findAll();
        assertFalse(cars.isEmpty());
        assertEquals(10, cars.size());
    }

    @Test
    public void findByIdTest() {
        CarDTO dto = this.carService.findById(5L);
        assertNotNull(dto);
        assertEquals("Carro 5", dto.getName());
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
        assertEquals(updated.getName(), this.carService.findById(2L).getName());
    }

    @Test
    public void deleteCarTest() {
        CarDTO dto = this.carService.findById(10L);
        assertNotNull(dto);

        Long id = dto.getId();
        assertNotNull(id);

        this.carService.delete(id);
    }
}
