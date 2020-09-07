package com.aegerm.springbootrestcars.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
}
