package com.aegerm.springbootrestcars.domain.dto;

import com.aegerm.springbootrestcars.domain.Car;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarDTO {

    private Long id;
    private String name;
    private String type;

    public static CarDTO create(Car car) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(car, CarDTO.class);
    }
}
