package com.aegerm.springbootrestcars.controller;

import com.aegerm.springbootrestcars.domain.Car;
import com.aegerm.springbootrestcars.domain.dto.CarDTO;
import com.aegerm.springbootrestcars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDTO>> findAll() {
        return ResponseEntity.ok(this.carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CarDTO>> findById(@PathVariable Long id) {
        Optional<CarDTO> car = this.carService.findById(id);
        return car.map(_car -> ResponseEntity.ok(car)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CarDTO>> findByName(@PathVariable String name) {
        List<CarDTO> cars = this.carService.findByName(name);
        return cars.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarDTO> insert(@RequestBody Car car) {
        CarDTO dto = this.carService.insert(car);
        return dto != null ? ResponseEntity.created(getUri(dto.getId())).build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Car car) {
        this.carService.update(id, car);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        this.carService.delete(id);
        return ResponseEntity.ok().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
