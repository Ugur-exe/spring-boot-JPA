package com.ugurkolcak.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugurkolcak.controller.ICustomerContoller;
import com.ugurkolcak.dto.request.CreateCustomerRequest;
import com.ugurkolcak.dto.request.UpdateCustomerRequest;
import com.ugurkolcak.dto.response.CustomerResponse;
import com.ugurkolcak.services.ICustomerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/rest/api/customer")
public class CustomerControllerImpl implements ICustomerContoller {
    private ICustomerService customerService;

    public CustomerControllerImpl(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    @Override
    public CustomerResponse getCustomerById(@PathVariable(name = "id") Long id) {
        return customerService.getCustomerById(id);
    }

    @Override
    @PostMapping("/save")
    public CustomerResponse saveCustomer(@RequestBody CreateCustomerRequest customer) {
        return customerService.saveCustomer(customer);
    }

    @Override
    @PutMapping("/update/{id}")
    public CustomerResponse updateCustomer(@PathVariable(name = "id") Long id,
            @RequestBody UpdateCustomerRequest customer) {
        return customerService.updateCustomer(id, customer);
    }

}
