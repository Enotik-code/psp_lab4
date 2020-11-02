package by.bsuir.service;

import by.bsuir.bean.Company;
import by.bsuir.repository.SwimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwimmerService {

    @Autowired
    SwimmerRepository swimmerRepository;

    public List<Company> getNewList() {
        return swimmerRepository.findAll().stream().sorted(Comparator.comparing(Company::getResult))
                .collect(Collectors.toList()).subList(0,3);
    }

}