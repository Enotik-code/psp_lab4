package by.bsuir.controller;

import by.bsuir.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompanySortController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping(value = "/sortByName")
    public ModelAndView sortByName(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.sortCompanyByName());
        return modelAndView;
    }

    @GetMapping(value = "/sortByCity")
    public ModelAndView sortByCity(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.sortCompanyByCity());
        return modelAndView;
    }

    @GetMapping(value = "/sortByType")
    public ModelAndView sortByType(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.sortCompanyByType());
        return modelAndView;
    }

    @GetMapping(value = "/sortByTaxAmount")
    public ModelAndView searchCompanyByTaxAmount(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.sortCompanyByTaxAmount());
        return modelAndView;
    }

    @GetMapping(value = "/filtrationTaxAmount")
    public ModelAndView searchCompanyByTaxAmount(@ModelAttribute(name = "resultMin") int resultMin,
                                                 @ModelAttribute(name = "resultMax") int resultMax){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listCompany", companyRepository.filtrationCompanyByTaxAmount(resultMin, resultMax));
        return modelAndView;
    }
}
