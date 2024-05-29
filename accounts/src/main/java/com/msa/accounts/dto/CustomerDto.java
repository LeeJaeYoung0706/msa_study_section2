package com.msa.accounts.dto;

import com.msa.accounts.entity.accounts.Accounts;
import lombok.Data;


@Data
public class CustomerDto {

    private String name;

    private String email;

    private String mobileNumber;

    private AccountsDto accountsDto;

}
