package com.ugurkolcak.converter;

import org.springframework.stereotype.Component;

import com.ugurkolcak.dto.request.AddressRequest;
import com.ugurkolcak.dto.request.UpdateCustomerRequest;
import com.ugurkolcak.dto.response.AddressResponse;
import com.ugurkolcak.dto.response.CityResponse;
import com.ugurkolcak.entities.Address;
import com.ugurkolcak.entities.Customer;

@Component
public class AddressConverter {

    public Address toEntity(AddressRequest entity) {
        return Address.builder()
                .desciption(entity.getDesciption())
                .build();
    }

    public AddressResponse toResponse(Address entity) {
        return AddressResponse.builder()
                .id(entity.getId())
                .desciption(entity.getDesciption())
                .city(entity.getCity() != null ? CityResponse.builder()
                        .id(entity.getCity().getId())
                        .name(entity.getCity().getName())
                        .code(entity.getCity().getCode())
                        .createdAt(entity.getCity().getCreatedAt())
                        .updatedAt(entity.getCity().getUpdatedAt())
                        .build()
                        : null)
                .build();
    }

    public void updateEntityFromDto(AddressRequest dto, Address entity) {
        entity.setDesciption(dto.getDesciption());
        entity.setCity(entity.getCity());

    }
}
