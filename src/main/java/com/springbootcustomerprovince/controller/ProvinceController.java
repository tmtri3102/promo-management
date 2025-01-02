package com.springbootcustomerprovince.controller;

import com.springbootcustomerprovince.model.Customer;
import com.springbootcustomerprovince.model.Province;
import com.springbootcustomerprovince.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController
{
    @Autowired
    private IProvinceService provinceService;

    // Read

    @GetMapping
    public ModelAndView listProvinces() {
        ModelAndView modelAndView = new ModelAndView("/provinces/list");
        modelAndView.addObject("provinces", provinceService.findAll());
        return modelAndView;
    }

    // Delete

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/delete");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("province") Province province) {
        provinceService.remove(province.getId());
        return "redirect:/customers";
    }

    // Create

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/provinces/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProvince(@ModelAttribute("province") Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("/provinces/create");
        modelAndView.addObject("province", new Customer());
        modelAndView.addObject("message", "Added a new province");
        return modelAndView;
    }


}
