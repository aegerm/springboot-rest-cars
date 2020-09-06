package com.aegerm.springbootrestcars.controller;

import com.aegerm.springbootrestcars.SpringbootRestCarsApplication;
import com.aegerm.springbootrestcars.domain.Car;
import com.aegerm.springbootrestcars.domain.dto.CarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringbootRestCarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<CarDTO> findCar(String url) {
        return this.restTemplate.getForEntity(url, CarDTO.class);
    }

    private ResponseEntity<List<CarDTO>> findAll(String url) {
        return this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDTO>>() {});
    }

    @Test
    public void insertCarTest() {
        Car car = new Car();
        car.setName("Carro 1");
        car.setType("Tipo 1");

        ResponseEntity response = this.restTemplate.postForEntity("/api/v1/cars", car, null);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String location = response.getHeaders().get("location").get(0);

        CarDTO dto = findCar(location).getBody();
        assertNotNull(dto);
        assertEquals("Carro 1", dto.getName());
        assertEquals("Tipo 1", dto.getType());

        this.restTemplate.delete(location);
        assertEquals(HttpStatus.NOT_FOUND, findCar(location).getStatusCode());
    }

    @Test
    public void findAll() {
        List<CarDTO> cars = findAll("/api/v1/cars").getBody();
        assertNotNull(cars);
        assertEquals(10, cars.size());
    }

    @Test
    public void getOkTest() {
        ResponseEntity<CarDTO> response = findCar("/api/v1/cars/2");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarDTO dto = response.getBody();
        assertEquals("Carro 2", dto.getName());
    }

    @Test
    public void getNotFoundTest() {
        ResponseEntity<CarDTO> response = findCar("/api/v1/cars/11");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
