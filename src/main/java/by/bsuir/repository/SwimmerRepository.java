package by.bsuir.repository;

import by.bsuir.bean.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, Integer> {
    Swimmer getByName(String name);
    List<Swimmer> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Swimmer s WHERE s.id = ?1")
    void deleteSwimmerById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Swimmer s set s.result = ?2 where s.id = ?1")
    void setResult(int id, int result);

    @Query(value = "SELECT s FROM Swimmer s WHERE s.name like %?1%")
    List<Swimmer> searchSwimmerByName(String name);

    @Query(value = "SELECT s FROM Swimmer s WHERE s.id = ?1")
    List<Swimmer> searchSwimmerById(int id);

    @Query(value = "SELECT s FROM Swimmer s WHERE s.result = ?1")
    List<Swimmer> searchSwimmerByResult(int result);

    @Query(value = "SELECT s FROM Swimmer s order by s.name")
    List<Swimmer> sortSwimmerByName();

    @Query(value = "SELECT s FROM Swimmer s order by s.result")
    List<Swimmer> sortSwimmerByResult();

    @Query(value = "SELECT s FROM Swimmer s order by s.country.name")
    List<Swimmer> sortSwimmerByCountry();

    @Query(value = "SELECT s FROM Swimmer s order by s.type.name")
    List<Swimmer> sortSwimmerByType();

    @Query(value = "SELECT s FROM Swimmer s where s.result between ?1 and ?2")
    List<Swimmer> filtrationSwimmerByName(int resultMin, int resultMax);


}
