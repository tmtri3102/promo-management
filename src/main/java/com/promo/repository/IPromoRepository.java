package com.promo.repository;

import com.promo.model.Promo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface IPromoRepository extends JpaRepository<Promo, Integer> {
    // Tìm kiếm theo mức khuyến mãi, ngày bắt đầu và ngày kết thúc
    List<Promo> findByDiscountGreaterThanEqual(Double discount);
    List<Promo> findByStartDateAfter(LocalDate startDate);
    List<Promo> findByEndDateBefore(LocalDate endDate);
    List<Promo> findByDiscountGreaterThanEqualAndStartDateAfterAndEndDateBefore(
            Double discount, LocalDate startDate, LocalDate endDate);
    Collection<Promo> findAllByDiscount(long discount);

    Collection<Promo> findAllByStartDate(LocalDate date);

    Collection<Promo> findAllByEndDate(LocalDate date);

    @Query(value = "from Promo where discount=:discount and startDate=:startDate and endDate=:endDate")
    Iterable<Promo> findAllByThreeFields(long discount, LocalDate startDate, LocalDate endDate);
}
