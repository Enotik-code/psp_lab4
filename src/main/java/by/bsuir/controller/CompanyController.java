package by.bsuir.controller;

import by.bsuir.bean.Company;
import by.bsuir.repository.CityRepository;
import by.bsuir.repository.CompanyRepository;
import by.bsuir.repository.TaxionSystemRepository;
import by.bsuir.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    TaxionSystemRepository taxionSystemRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    TaxService taxService;

    @GetMapping(value = "/list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/searchName")
    public ModelAndView searchCompanyByName(@ModelAttribute(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.searchCompanyByName(name));
        return modelAndView;
    }

    @GetMapping(value = "/searchId")
    public ModelAndView searchCompanyById(@ModelAttribute(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.searchCompanyById(id));
        return modelAndView;
    }

    @GetMapping(value = "/searchTaxAmount")
    public ModelAndView searchCompanyByTaxAmount(@ModelAttribute(name = "result") int result) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.searchCompanyByTaxAmount(result));
        return modelAndView;
    }

    @GetMapping(value = "/addCompany")
    public ModelAndView addCompanyPage() {
        ModelAndView modelAndView = new ModelAndView("addCompany");
        modelAndView.addObject("cityList", cityRepository.findAll());
        modelAndView.addObject("typeList", taxionSystemRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/delete" + "/{id}")
    public ModelAndView deleteCompany(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        companyRepository.deleteCompanyById(id);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @GetMapping(value = "/update" + "/{id}")
    public ModelAndView updateCompany(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/updateCompany");
        modelAndView.addObject("cityList", cityRepository.findAll());
        modelAndView.addObject("typeList", taxionSystemRepository.findAll());
        return modelAndView;
    }

    @PostMapping(value = "/update" + "/{id}")
    public ModelAndView addCompanyPagePost(@PathVariable(name = "id") int id,
                                           @RequestParam(value = "city", required = false) String city,
                                           @RequestParam(value = "profit", required = false) int profit,
                                           @RequestParam(value = "taxationSystem", required = false) String type) {
        ModelAndView modelAndView = new ModelAndView("/updateCompany");
        companyRepository.setNewData(id, profit,cityRepository.findByName(city) ,taxionSystemRepository.findByName(type),
                taxService.getTaxAmount(profit, taxionSystemRepository.findByName(type).getPercent()));
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }


    @PostMapping(value = "/addCompany")
    public ModelAndView addCompanyPagePost(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "city", required = false) String country,
                                           @RequestParam(value = "profit", required = false) int profit,
                                           @RequestParam(value = "taxationSystem", required = false) String type) {
        ModelAndView modelAndView = new ModelAndView("addCompany");
        Company company = new Company(name, cityRepository.findByName(country),
                taxionSystemRepository.findByName(type), profit, taxService.getTaxAmount(profit, taxionSystemRepository.findByName(type).getPercent()));
        companyRepository.save(company);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }
}
