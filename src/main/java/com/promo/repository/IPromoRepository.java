package com.promo.repository;

import com.promo.model.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPromoRepository extends JpaRepository<Promo, Integer> {
    // Tìm kiếm theo mức khuyến mãi, ngày bắt đầu và ngày kết thúc
    List<Promo> findByDiscountGreaterThanEqual(Double discount);
    List<Promo> findByStartDateAfter(LocalDate startDate);
    List<Promo> findByEndDateBefore(LocalDate endDate);
    List<Promo> findByDiscountGreaterThanEqualAndStartDateAfterAndEndDateBefore(
            Double discount, LocalDate startDate, LocalDate endDate);
}
