package com.ugurkolcak.services;

import com.ugurkolcak.dto.request.CreateCustomerRequest;
import com.ugurkolcak.dto.request.UpdateCustomerRequest;
import com.ugurkolcak.dto.response.CustomerResponse;

public interface ICustomerService {

    public CustomerResponse saveCustomer(CreateCustomerRequest customer);
    public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest customer);

    public CustomerResponse getCustomerById(Long id);

}
