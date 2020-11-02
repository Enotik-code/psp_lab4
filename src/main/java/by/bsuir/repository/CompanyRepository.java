package by.bsuir.repository;

import by.bsuir.bean.City;
import by.bsuir.bean.Company;
import by.bsuir.bean.TaxationSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company getByName(String name);
    Company getById(int id);
    List<Company> findAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Company s WHERE s.id = ?1")
    void deleteCompanyById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Company s set s.city = ?3, s.profit = ?2, s.taxationSystem = ?4, s.taxAmount = ?5 where s.id = ?1")
    void setNewData(int id, int result, City city, TaxationSystem taxationSystem, int taxAmount);

    @Query(value = "SELECT s FROM Company s WHERE s.name like %?1%")
    List<Company> searchCompanyByName(String name);

    @Query(value = "SELECT s FROM Company s WHERE s.id = ?1")
    List<Company> searchCompanyById(int id);

    @Query(value = "SELECT s FROM Company s WHERE s.taxAmount = ?1")
    List<Company> searchCompanyByTaxAmount(int result);

    @Query(value = "SELECT s FROM Company s order by s.name")
    List<Company> sortCompanyByName();

    @Query(value = "SELECT s FROM Company s order by s.taxAmount")
    List<Company> sortCompanyByTaxAmount();

    @Query(value = "SELECT s FROM Company s order by s.city")
    List<Company> sortCompanyByCity();

    @Query(value = "SELECT s FROM Company s order by s.taxationSystem.name")
    List<Company> sortCompanyByType();

    @Query(value = "SELECT s FROM Company s where s.taxAmount between ?1 and ?2")
    List<Company> filtrationCompanyByTaxAmount(int resultMin, int resultMax);


}
