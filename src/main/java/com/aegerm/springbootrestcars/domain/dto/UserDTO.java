package com.aegerm.springbootrestcars.domain.dto;

import com.aegerm.springbootrestcars.domain.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private String email;
    private String token;

    public static UserDTO create(User user) {
        ModelMapper mapper = new ModelMapper();
        UserDTO dto = mapper.map(user, UserDTO.class);
        return dto;
    }

    public static UserDTO create(User user, String token) {
        UserDTO dto = create(user);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
