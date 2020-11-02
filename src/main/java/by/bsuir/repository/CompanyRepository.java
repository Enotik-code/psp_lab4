package by.bsuir.repository;

import by.bsuir.bean.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SwimmerRepository extends JpaRepository<Company, Integer> {
    Company getByName(String name);
    List<Company> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Company s WHERE s.id = ?1")
    void deleteSwimmerById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Company s set s.result = ?2 where s.id = ?1")
    void setResult(int id, int result);

    @Query(value = "SELECT s FROM Company s WHERE s.name like %?1%")
    List<Company> searchSwimmerByName(String name);

    @Query(value = "SELECT s FROM Company s WHERE s.id = ?1")
    List<Company> searchSwimmerById(int id);

    @Query(value = "SELECT s FROM Company s WHERE s.result = ?1")
    List<Company> searchSwimmerByResult(int result);

    @Query(value = "SELECT s FROM Company s order by s.name")
    List<Company> sortSwimmerByName();

    @Query(value = "SELECT s FROM Company s order by s.result")
    List<Company> sortSwimmerByResult();

    @Query(value = "SELECT s FROM Company s order by s.country.name")
    List<Company> sortSwimmerByCountry();

    @Query(value = "SELECT s FROM Company s order by s.type.name")
    List<Company> sortSwimmerByType();

    @Query(value = "SELECT s FROM Company s where s.result between ?1 and ?2")
    List<Company> filtrationSwimmerByName(int resultMin, int resultMax);


}
