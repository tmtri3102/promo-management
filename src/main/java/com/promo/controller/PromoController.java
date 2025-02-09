package com.promo.controller;

import com.promo.model.Promo;
import com.promo.service.IPromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import java.util.HashMap;

@Controller
@RequestMapping("/promo")
public class PromoController {
    @Autowired
    private IPromoService promoService;

    @GetMapping
    public ModelAndView listPromo(@RequestParam("search") Optional<String> search) {
        ModelAndView modelAndView = new ModelAndView("list");
//        Iterable<Promo> promos;
//        if (search.isPresent() && !search.get().isEmpty()) {
//            promos = promoService.searchPromos(search.get());
//            if (((List<Promo>) promos).isEmpty()) {
//                modelAndView.addObject("message", "No promotions found");
//            }
//        } else {
//            promos = promoService.findAll();
//        }
        Iterable<Promo> promos = promoService.findAll();
        modelAndView.addObject("promos", promos);
        return modelAndView;
    }

    // SEARCH
    @GetMapping("/search_discount")
    public String searchPromosByDiscount(@RequestParam("search") String discount, Model model) {
        long amount = Long.parseLong(discount);
        model.addAttribute("promos", promoService.findAllByDiscount(amount));
        return "list";
    }

    @GetMapping("/search_start_date")
    public String searchPromosByStartDate(@RequestParam("search") String date, Model model) {
        LocalDate startDate = LocalDate.parse(date);
        model.addAttribute("promos", promoService.findAllByStartDate(startDate));
        return "list";
    }

    @GetMapping("/search_end_date")
    public String searchPromosByEndDate(@RequestParam("search") String date, Model model) {
        LocalDate startDate = LocalDate.parse(date);
        model.addAttribute("promos", promoService.findAllByEndDate(startDate));
        return "list";
    }

    @GetMapping("/search_all")
    public String searchPromosBy3Fields(@RequestParam long discount, @RequestParam LocalDate start_date, @RequestParam LocalDate end_date, Model model) {

        Iterable<Promo> vouchers = promoService.findAllByThreeFields(discount, start_date, end_date);

        model.addAttribute("promos", vouchers);
        return "list";
    }


    // Create
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("promo", new Promo());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView savePromo(@ModelAttribute("promo") Promo promo) {
        LocalDate now = LocalDate.now();
        ModelAndView modelAndView = new ModelAndView("/create");
        try {
            promo.setStartDate(LocalDate.parse(promo.getStartDate().toString()));
            promo.setEndDate(LocalDate.parse(promo.getEndDate().toString()));
        } catch (DateTimeParseException e) {
            modelAndView.addObject("errorStartDate", "Invalid date format.");
            modelAndView.addObject("errorEndDate", "Invalid date format.");
            return modelAndView;
        }
        // Kiểm tra điều kiện
        if (promo.getStartDate().isBefore(now)) {
            modelAndView.addObject("errorStartDate", "Start date must be today or in the future.");
            return modelAndView;
        }

        if (promo.getEndDate().isBefore(promo.getStartDate().plusDays(1))) {
            modelAndView.addObject("errorEndDate", "End date must be at least one day after start date.");
            return modelAndView;
        }
        promoService.save(promo);
        modelAndView.addObject("promo", new Promo());
        modelAndView.addObject("message", "Added a new promo");

        return modelAndView;
//        promoService.save(promo);
//        ModelAndView modelAndView = new ModelAndView("/create");
//        modelAndView.addObject("promo", new Promo());
//        modelAndView.addObject("message", "Added a new promo");
//        return modelAndView;
    }

    // Update
    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable int id) {
        Optional<Promo> promo = promoService.findById(id);
        if (promo.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/update");
            modelAndView.addObject("promo", promo.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PutMapping("/update")
    public ModelAndView updatePromo(@ModelAttribute("promo") Promo promo) {
        promoService.save(promo);
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("promo", promo);
        modelAndView.addObject("message", "Promo updated successfully");
        return modelAndView;
    }

    // Delete
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Optional<Promo> promo = promoService.findById(id);
        if (promo.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("promo", promo.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/delete")
    public String deletePromo(@ModelAttribute("promo") Promo promo) {
        promoService.remove(promo.getId());
        return "redirect:/promo";
    }
}
