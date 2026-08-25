package br.com.firstclass.spring_boot_essentials.exception;


import jakarta.validation.constraints.AssertFalse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ErrorResponse {

    private String message;
    private Integer status;


}
