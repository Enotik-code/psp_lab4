package by.bsuir.controller;

import by.bsuir.repository.SwimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Action;

@Controller
public class SwimmerSortController {

    @Autowired
    SwimmerRepository swimmerRepository;

    @GetMapping(value = "/sortByName")
    public ModelAndView sortByName(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.sortSwimmerByName());
        return modelAndView;
    }

    @GetMapping(value = "/sortByCountry")
    public ModelAndView sortByCountry(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.sortSwimmerByCountry());
        return modelAndView;
    }

    @GetMapping(value = "/sortByType")
    public ModelAndView sortByType(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.sortSwimmerByType());
        return modelAndView;
    }

    @GetMapping(value = "/sortByResult")
    public ModelAndView searchSwimmerByResult(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.sortSwimmerByResult());
        return modelAndView;
    }

    @GetMapping(value = "/filtrationResult")
    public ModelAndView searchSwimmerByResult(@ModelAttribute(name = "resultMin") int resultMin,
                                              @ModelAttribute(name = "resultMax") int resultMax){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("listSwimmer", swimmerRepository.filtrationSwimmerByName(resultMin, resultMax));
        return modelAndView;
    }
}
