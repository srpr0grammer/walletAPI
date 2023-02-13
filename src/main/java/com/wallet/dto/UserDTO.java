package com.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "Riquired field.")
    private String name;

    @NotNull(message = "Riquired field.")
    private String password;

    @NotNull(message = "Riquired field.")
    @Email(message = "Enter the email in the correct format.")
    private String email;
}
