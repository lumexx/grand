package com.example.grand.account.controller;

import com.example.grand.account.dto.TransferLogDto;
import com.example.grand.account.dto.request.TransferDto;
import com.example.grand.account.service.AccountService;
import com.example.grand.common.Routes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping(Routes.TRANSFER)
    public TransferLogDto transferTo(@Valid @RequestBody TransferDto request) {
        return service.transfer(request);
    }

}
