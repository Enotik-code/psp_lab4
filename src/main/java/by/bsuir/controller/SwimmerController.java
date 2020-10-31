package by.bsuir.controller;

import by.bsuir.bean.Country;
import by.bsuir.bean.Swimmer;
import by.bsuir.repository.CountryRepository;
import by.bsuir.repository.SwimmerRepository;
import by.bsuir.repository.TypeRepository;
import by.bsuir.service.SwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SwimmerController {

    @Autowired
    SwimmerRepository swimmerRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    SwimmerService swimmerService;

    @GetMapping(value = "/list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/searchName")
    public ModelAndView searchSwimmerByName(@ModelAttribute(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.searchSwimmerByName(name));
        return modelAndView;
    }

    @GetMapping(value = "/searchId")
    public ModelAndView searchSwimmerById(@ModelAttribute(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.searchSwimmerById(id));
        return modelAndView;
    }

    @GetMapping(value = "/searchResult")
    public ModelAndView searchSwimmerByResult(@ModelAttribute(name = "result") int result) {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.searchSwimmerByResult(result));
        return modelAndView;
    }

    @GetMapping(value = "/addSwimmer")
    public ModelAndView addSwimmerPage() {
        ModelAndView modelAndView = new ModelAndView("/addSwimmer");
        modelAndView.addObject("countryList", countryRepository.findAll());
        modelAndView.addObject("typeList", typeRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/addResult")
    public ModelAndView addResultPage() {
        ModelAndView modelAndView = new ModelAndView("/addResult");
        modelAndView.addObject("listSwimmer", swimmerRepository.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/delete" + "/{id}")
    public ModelAndView deleteSwimmer(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("/list");
        swimmerRepository.deleteSwimmerById(id);
        return modelAndView;
    }

    @PostMapping(value = "/addResult")
    public ModelAndView addResultPagePost(@RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "result", required = false) int result) {
        ModelAndView modelAndView = new ModelAndView("/addResult");
        swimmerRepository.setResult(swimmerRepository.getByName(name).getId(), result);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @PostMapping(value = "/addSwimmer")
    public ModelAndView addSwimmerPagePost(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "country", required = false) String country,
                                           @RequestParam(value = "type", required = false) String type) {
        ModelAndView modelAndView = new ModelAndView("/addSwimmer");
        Swimmer swimmer = new Swimmer(name, countryRepository.findByName(country), typeRepository.findByName(type));
        swimmerRepository.save(swimmer);
        modelAndView.setViewName("redirect:/list");
        return modelAndView;
    }

    @GetMapping(value = "/result")
    public ModelAndView resultPage() {
        ModelAndView modelAndView = new ModelAndView("/result");
        modelAndView.addObject("firstPlace", swimmerService.getNewList().get(0));
        modelAndView.addObject("secondPlace", swimmerService.getNewList().get(1));
        modelAndView.addObject("thirdPlace", swimmerService.getNewList().get(2));
        return modelAndView;
    }

}
