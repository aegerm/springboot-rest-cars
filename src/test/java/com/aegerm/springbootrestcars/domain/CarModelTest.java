package com.aegerm.springbootrestcars.domain;

import com.aegerm.springbootrestcars.domain.dto.CarDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarModelTest {

    @Test
    public void carModelMapper() {
        Car car = new Car();
        assertEquals(car.getId(), CarDTO.create(car).getId());
        assertEquals(car.getName(), CarDTO.create(car).getName());
        assertEquals(car.getType(), CarDTO.create(car).getType());
    }
}
