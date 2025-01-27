package com.promo.service;

import com.promo.model.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface IPromoService extends IGenerateService<Promo> {
//    Page<Promo> findAll(Pageable pageable);
//    Page<Promo> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
//    List<Promo> searchPromos(Double discount, LocalDate startDate, LocalDate endDate);
    List<Promo> searchPromos(String search);
    Collection<? extends Promo> findAllByDiscount(long discount);
    Collection<? extends Promo> findAllByStartDate(LocalDate date);
    Collection<? extends Promo> findAllByEndDate(LocalDate date);

    Iterable<Promo> findAllByThreeFields(long discount, LocalDate startDate, LocalDate endDate);
}
