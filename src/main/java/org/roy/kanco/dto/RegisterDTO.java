package org.roy.kanco.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

}
