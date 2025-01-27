package com.promo.service;

import com.promo.model.Promo;
import com.promo.repository.IPromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromoService implements IPromoService {

    @Autowired
    private IPromoRepository promoRepository;


    @Override
    public Iterable<Promo> findAll() {
        return promoRepository.findAll();
    }

    @Override
    public Optional<Promo> findById(Integer id) {
        return promoRepository.findById(id);
    }

    @Override
    public void save(Promo promo) {
        promoRepository.save(promo);
    }

    @Override
    public void remove(Integer id) {
        promoRepository.deleteById(id);
    }

//    public List<Promo> searchPromos(Double discount, LocalDate startDate, LocalDate endDate) {
//        return promoRepository.findByDiscountGreaterThanEqualAndStartDateAfterAndEndDateBefore(discount, startDate, endDate);
//    }

    @Override
    public List<Promo> searchPromos(String search) {
        List<Promo> results = new ArrayList<>();

        // Tách các phần từ trong chuỗi tìm kiếm
        String[] params = search.split(",");
        for (String param : params) {
            param = param.trim();
            if (param.matches("\\d+(\\.\\d+)?")) { // Kiểm tra nếu là discount
                Double discount = Double.valueOf(param);
                results.addAll(promoRepository.findByDiscountGreaterThanEqual(discount));
            } else if (param.matches("\\d{4}-\\d{2}-\\d{2}")) { // Kiểm tra nếu là date
                LocalDate date = LocalDate.parse(param);
                results.addAll(promoRepository.findByStartDateAfter(date));
                results.addAll(promoRepository.findByEndDateBefore(date));
            }
        }

        // Lọc kết quả duy nhất
        return results.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Collection<? extends Promo> findAllByDiscount(long discount) {
        return promoRepository.findAllByDiscount(discount);
    }

    @Override
    public Collection<? extends Promo> findAllByStartDate(LocalDate date) {
        return promoRepository.findAllByStartDate(date);
    }

    @Override
    public Collection<? extends Promo> findAllByEndDate(LocalDate date) {
        return promoRepository.findAllByEndDate(date);
    }

    @Override
    public Iterable<Promo> findAllByThreeFields(long discount, LocalDate startDate, LocalDate endDate) {
        return promoRepository.findAllByThreeFields(discount, startDate, endDate);
    }
//    @Override
//    public Page<Promo> findAll(Pageable pageable) {
//        return promoRepository.findAll(pageable);
//    }
//
//    @Override
//    public Page<Promo> findAllByNameContainingIgnoreCase(Pageable pageable, String name) {
//        return promoRepository.findAllByNameContainingIgnoreCase(pageable, name);
//    }
}
