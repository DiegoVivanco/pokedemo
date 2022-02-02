package com.example.pokedemo.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Diego Vivanco (xx706)
 * @since 29/04/2020
 */

@Data
@AllArgsConstructor
public class ExternalServiceError implements Serializable {
    private static final long serialVersionUID = 1L;
    private String serviceName;
    private String request;
    private String response;
}