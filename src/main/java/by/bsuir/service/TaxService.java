package by.bsuir.service;

import by.bsuir.repository.CompanyRepository;
import by.bsuir.repository.TaxionSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaxService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    TaxionSystemRepository taxionSystemRepository;

    public int getTaxAmount(int profit, int percent) {
    return profit * percent / 100;
    }

}
