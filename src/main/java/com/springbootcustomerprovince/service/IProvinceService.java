package com.springbootcustomerprovince.service;

import com.springbootcustomerprovince.model.DTO.CountCustomerDTO;
import com.springbootcustomerprovince.model.Province;

public interface IProvinceService extends IGenerateService<Province> {
    Iterable<CountCustomerDTO> countCustomerByProvince();

    void deleteProvince(int id);
}
