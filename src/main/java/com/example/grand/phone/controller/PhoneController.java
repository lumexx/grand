package com.example.grand.phone.controller;

import com.example.grand.common.Constant;
import com.example.grand.common.Routes;
import com.example.grand.phone.dto.PhoneDataDto;
import com.example.grand.phone.service.PhoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService service;

    @PostMapping(Routes.PHONES)
    public PhoneDataDto create(@RequestBody @Valid PhoneDataDto dto) {
        return service.create(dto);
    }

    @PutMapping(Routes.PHONES_UUID)
    public PhoneDataDto update(@PathVariable(Constant.UUID) UUID uuid, @RequestBody @Valid PhoneDataDto dto) {
        dto.setUuid(uuid);
        return service.update(dto);
    }

    @DeleteMapping(Routes.PHONES_UUID)
    public void delete(@PathVariable(Constant.UUID) UUID uuid) {
        service.delete(uuid);
    }

}
