package com.springbootcustomerprovince.repository;

import com.springbootcustomerprovince.model.Province;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, Integer> {
    Page<Province> findAll(Pageable pageable);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "call deleteProvinceById(:id)")
    void deleteProvinceById(@Param("id") Integer id);
}
