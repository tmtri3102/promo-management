package com.springbootcustomerprovince.repository;

import com.springbootcustomerprovince.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
}
