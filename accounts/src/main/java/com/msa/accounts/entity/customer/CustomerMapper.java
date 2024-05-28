package com.msa.accounts.entity.customer;

import com.msa.accounts.dto.CustomerDto;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        return Customer.createdCustomer(customerDto.getName(), customerDto.getEmail(), customerDto.getMobileNumber());
    }
}
