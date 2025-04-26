package com.example.grand.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserFilter {

    private LocalDate dateOfBirth;
    private String name;
    private String email;
    private String phone;

}
