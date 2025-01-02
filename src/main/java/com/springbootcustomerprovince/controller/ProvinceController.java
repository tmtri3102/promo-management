package com.springbootcustomerprovince.controller;

import com.springbootcustomerprovince.model.Customer;
import com.springbootcustomerprovince.model.Province;
import com.springbootcustomerprovince.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Customer());
        modelAndView.addObject("message", "Added a new province");
        return modelAndView;
    }


}
