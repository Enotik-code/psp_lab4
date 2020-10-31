package by.bsuir.repository;

import by.bsuir.bean.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findAll();
    Country findByName(String name);
}
