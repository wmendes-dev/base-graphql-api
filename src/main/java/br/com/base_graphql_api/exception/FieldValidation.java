package br.com.base_graphql_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldValidation {

    private String field;

    private String message;

}
