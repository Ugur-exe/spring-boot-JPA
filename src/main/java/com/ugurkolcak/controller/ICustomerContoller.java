package com.ugurkolcak.controller;

import com.ugurkolcak.dto.request.CreateCustomerRequest;
import com.ugurkolcak.dto.request.UpdateCustomerRequest;
import com.ugurkolcak.dto.response.CustomerResponse;

public interface ICustomerContoller {
    public CustomerResponse getCustomerById(Long id);
public CustomerResponse updateCustomer(Long id,UpdateCustomerRequest customer);
    public CustomerResponse saveCustomer(CreateCustomerRequest customer);
}
