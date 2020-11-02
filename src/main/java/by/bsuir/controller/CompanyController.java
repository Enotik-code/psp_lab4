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
public class SwimmerController {

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
        modelAndView.addObject("listSwimmer", companyRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/searchName")
    public ModelAndView searchSwimmerByName(@ModelAttribute(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", companyRepository.searchSwimmerByName(name));
        return modelAndView;
    }

    @GetMapping(value = "/searchId")
    public ModelAndView searchSwimmerById(@ModelAttribute(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", companyRepository.searchSwimmerById(id));
        return modelAndView;
    }

    @GetMapping(value = "/searchResult")
    public ModelAndView searchSwimmerByResult(@ModelAttribute(name = "result") int result) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", companyRepository.searchSwimmerByResult(result));
        return modelAndView;
    }

    @GetMapping(value = "/addSwimmer")
    public ModelAndView addSwimmerPage() {
        ModelAndView modelAndView = new ModelAndView("/addSwimmer");
        modelAndView.addObject("countryList", cityRepository.findAll());
        modelAndView.addObject("typeList", taxionSystemRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/addResult")
    public ModelAndView addResultPage() {
        ModelAndView modelAndView = new ModelAndView("/addResult");
        modelAndView.addObject("listSwimmer", companyRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/delete" + "/{id}")
    public ModelAndView deleteSwimmer(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/list");
        companyRepository.deleteSwimmerById(id);
        return modelAndView;
    }

    @PostMapping(value = "/addResult")
    public ModelAndView addResultPagePost(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "result", required = false) int result) {
        ModelAndView modelAndView = new ModelAndView("/addResult");
        companyRepository.setResult(companyRepository.getByName(name).getId(), result);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @PostMapping(value = "/addSwimmer")
    public ModelAndView addSwimmerPagePost(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "country", required = false) String country,
                                           @RequestParam(value = "type", required = false) String type) {
        ModelAndView modelAndView = new ModelAndView("/addSwimmer");
        Company company = new Company(name, cityRepository.findByName(country), taxionSystemRepository.findByName(type), 12);
        companyRepository.save(company);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @GetMapping(value = "/result")
    public ModelAndView resultPage() {
        ModelAndView modelAndView = new ModelAndView("/result");
        modelAndView.addObject("firstPlace", taxService.getNewList().get(0));
        modelAndView.addObject("secondPlace", taxService.getNewList().get(1));
        modelAndView.addObject("thirdPlace", taxService.getNewList().get(2));
        return modelAndView;
    }

}
