package com.assignment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginModel {

    private String email;
    private String password;
}
