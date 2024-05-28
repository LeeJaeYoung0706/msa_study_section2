package com.msa.accounts.service;

import com.msa.accounts.dto.CustomerDto;

// I로 시작하면 인터페이스 vs Impl
public interface IAccountService {
    /**
     * account 생성
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
}
