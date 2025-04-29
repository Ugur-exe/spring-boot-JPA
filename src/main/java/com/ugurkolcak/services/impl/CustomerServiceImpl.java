package com.ugurkolcak.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ugurkolcak.converter.AddressConverter;
import com.ugurkolcak.converter.CustomerConverter;
import com.ugurkolcak.dto.request.CreateCustomerRequest;
import com.ugurkolcak.dto.request.UpdateCustomerRequest;
import com.ugurkolcak.dto.response.CustomerResponse;
import com.ugurkolcak.entities.Address;
import com.ugurkolcak.entities.City;
import com.ugurkolcak.entities.Customer;
import com.ugurkolcak.exception.ResourceNotFoundException;
import com.ugurkolcak.repository.AddressRepository;
import com.ugurkolcak.repository.CityRepository;
import com.ugurkolcak.repository.CustomerRepository;
import com.ugurkolcak.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private CustomerRepository customerRepository;
    private CustomerConverter customerConverter;
    private AddressRepository addressRepository;
    private AddressConverter addressConverter;
    private final CityRepository cityRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerConverter customerConverter,
            AddressRepository addressRepository, AddressConverter addressConverter, CityRepository cityRepository) {
        this.addressConverter = addressConverter;
        this.addressRepository = addressRepository;
        this.customerConverter = customerConverter;
        this.customerRepository = customerRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id) {
        Customer dbCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cusotmer", "id", id));

        return customerConverter.toResponse(dbCustomer);
    }

    @Override
    @Transactional
    public CustomerResponse saveCustomer(CreateCustomerRequest customerRequest) {

        String cityId = customerRequest.getAddress().getCityId();
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + cityId));

        Address address = addressConverter.toEntity(customerRequest.getAddress());
        address.setCity(city);

        address = addressRepository.save(address);

        Customer customerEntity = customerConverter.toEntity(customerRequest);
        customerEntity.setAddress(address);

        Customer dbCustomer = customerRepository.save(customerEntity);

        return customerConverter.toResponse(dbCustomer);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest customerRequest) {

        Customer dbCustomer = findCustomerById(id);
        System.out.println("DB Customer Name  " + dbCustomer.getName() + " ID: " + dbCustomer.getId() + " Address ID: "
                + dbCustomer.getAddress().getId());

        Long addressId = dbCustomer.getAddress().getId();
        customerConverter.updateEntityFromDto(customerRequest, dbCustomer);
        Address address = dbCustomer.getAddress();
        address.setId(addressId);
        System.out.println(
                "DB UPDATE Customer Name  " + dbCustomer.getName() + " ID: " + dbCustomer.getId() + " Address ID: "
                        + dbCustomer.getAddress().getId());

        System.out.println("DB Address  " + address);

        addressConverter.updateEntityFromDto(customerRequest.getAddress(), address);

        System.out.println("Address ID: " + address.getId());

        String newCityId = customerRequest.getAddress().getCityId();
        if (newCityId != null && (address.getCity() == null || !newCityId.equals(address.getCity().getId()))) {
            City newCity = cityRepository.findById(newCityId)
                    .orElseThrow(() -> new RuntimeException("City not found with id: " + newCityId));
            address.setCity(newCity);
        }

        addressRepository.save(address);
        dbCustomer.setAddress(address);
        Customer updatedCustomer = customerRepository.save(dbCustomer);

        return customerConverter.toResponse(updatedCustomer);
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

}
