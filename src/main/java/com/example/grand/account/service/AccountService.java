package com.example.grand.account.service;

import com.example.grand.account.dto.TransferLogDto;
import com.example.grand.account.dto.request.TransferDto;

public interface AccountService {
    TransferLogDto transfer(TransferDto dto);

}
