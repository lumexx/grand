package com.example.grand.email.controller;

import com.example.grand.common.Constant;
import com.example.grand.common.Routes;
import com.example.grand.email.dto.EmailDataDto;
import com.example.grand.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    @PostMapping(Routes.EMAILS)
    public EmailDataDto create(@RequestBody @Valid EmailDataDto dto) {
        return service.create(dto);
    }

    @PutMapping(Routes.EMAILS_UUID)
    public EmailDataDto update(@PathVariable(Constant.UUID) UUID uuid, @RequestBody @Valid EmailDataDto dto) {
        dto.setUuid(uuid);
        return service.update(dto);
    }
    @DeleteMapping(Routes.EMAILS_UUID)
    public void delete(@PathVariable(Constant.UUID) UUID uuid) {
        service.delete(uuid);
    }

}
