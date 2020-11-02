package by.bsuir.repository;

import by.bsuir.bean.TaxationSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<TaxationSystem, Integer> {
    List<TaxationSystem> findAll();
    TaxationSystem findByName(String name);
}
