package com.ugurkolcak.converter;

import org.springframework.stereotype.Component;

import com.ugurkolcak.dto.request.CreateCustomerRequest;

import com.ugurkolcak.dto.request.UpdateCustomerRequest;
import com.ugurkolcak.dto.response.CustomerResponse;

import com.ugurkolcak.entities.Customer;


@Component
public class CustomerConverter {
    private final AddressConverter addressConverter;

    public CustomerConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public Customer toEntity(CreateCustomerRequest dto) {
        return Customer.builder()
                .name(dto.getName())
                .address(addressConverter.toEntity(dto.getAddress()))
                .build();
    }

    public CustomerResponse toResponse(Customer entity) {
        return CustomerResponse.builder()
                .name(entity.getName())
                .address(addressConverter.toResponse(entity.getAddress()))
                .build();
    }

    public void updateEntityFromDto(UpdateCustomerRequest dto, Customer entity) {
        entity.setName((dto.getName()));
        entity.setAddress(addressConverter.toEntity(dto.getAddress()));

    }

}
