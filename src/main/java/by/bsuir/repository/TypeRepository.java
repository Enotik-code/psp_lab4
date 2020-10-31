package by.bsuir.repository;

import by.bsuir.bean.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findAll();
    Type findByName(String name);
}
