package com.springbootcustomerprovince.repository;

import com.springbootcustomerprovince.model.DTO.CountCustomerDTO;
import com.springbootcustomerprovince.model.Province;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "call deleteProvinceById(:id)")
    void deleteProvinceById(@Param("id") Integer id);

//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value = "call countCustomerByProvince()")
//    Iterable<CountCustomerDTO> countCustomerByProvince();
@Query(nativeQuery = true, value="select p.id, p.name, count(*) count from provinces p left join customers c on p.id = c.province_id group by p.id")
Iterable<CountCustomerDTO> countCustomerByProvince();
}
