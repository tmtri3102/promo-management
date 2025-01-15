package com.promo.repository;

import com.promo.model.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromoRepository extends JpaRepository<Promo, Integer> {

}
