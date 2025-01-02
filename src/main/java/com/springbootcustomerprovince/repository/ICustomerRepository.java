package com.springbootcustomerprovince.repository;

import com.springbootcustomerprovince.model.Customer;
import com.springbootcustomerprovince.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    Iterable<Customer> findAllByProvince(Province province);

}
