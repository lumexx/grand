package com.example.grand.user.dto;

import com.example.grand.common.dto.IndexedDto;
import com.example.grand.email.dto.EmailDataDto;
import com.example.grand.phone.dto.PhoneDataDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserDto extends IndexedDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private LocalDate dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PhoneDataDto> phones;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<EmailDataDto> emails;

}
