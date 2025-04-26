package com.example.grand.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@Setter
@FieldNameConstants
public class IndexedDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

}
