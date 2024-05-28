package com.msa.accounts.service.impl;

import com.msa.accounts.AccountsConstants;
import com.msa.accounts.dto.AccountsDto;
import com.msa.accounts.dto.CustomerDto;
import com.msa.accounts.entity.accounts.Accounts;
import com.msa.accounts.entity.accounts.AccountsMapper;
import com.msa.accounts.entity.customer.Customer;
import com.msa.accounts.entity.customer.CustomerMapper;
import com.msa.accounts.exceptions.CustomerAlreadyExistsException;
import com.msa.accounts.repository.AccountsRepository;
import com.msa.accounts.repository.CustomerRepository;
import com.msa.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements IAccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    /**
     * account 생성
     *
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (byMobileNumber.isPresent()){
            throw new CustomerAlreadyExistsException("이미 존재하는 고객 전화번호입니다.");
        }

        Customer savedCustomer = customerRepository.save(customer);
        log.info("test11");
        Accounts newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }

    /**
     * @param customer - Customer Object
     * @return 새로운 계정
     */
    private Accounts createNewAccount(Customer customer) {
        Long customerId = customer.getCustomerId();
        AccountsDto accountsDto = new AccountsDto();
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accountsDto.setAccountNumber(randomAccNumber);
        accountsDto.setBranchAddress(AccountsConstants.ADDRESS);
        accountsDto.setAccountType(AccountsConstants.SAVINGS);
        Accounts accounts = AccountsMapper.mapToAccounts(accountsDto, customerId);
        log.info("test = {}" , accounts.toString());
        return accounts;
    }
}
