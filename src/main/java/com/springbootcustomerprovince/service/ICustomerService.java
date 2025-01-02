package com.springbootcustomerprovince.service;

import com.springbootcustomerprovince.model.Customer;
import com.springbootcustomerprovince.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenerateService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAll(Pageable pageable);
}
