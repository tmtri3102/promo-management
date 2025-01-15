package com.promo.service;

import com.promo.model.Promo;
import com.promo.repository.IPromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
