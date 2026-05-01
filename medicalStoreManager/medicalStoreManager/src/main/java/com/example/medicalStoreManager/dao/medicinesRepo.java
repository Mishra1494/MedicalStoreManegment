package com.example.medicalStoreManager.dao;

import com.example.medicalStoreManager.Entity.Medicines;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface medicinesRepo extends CrudRepository<Medicines, Integer> {
    List<Medicines> findByNameAndCompany(String name,String Company);


    @Query("select m from Medicines m")
    List<Medicines> getAllMedicines();

    List<Medicines> findByCompany(String company);

    @Query("select m from Medicines m where m.expiryDate < CURRENT_DATE")
    List<Medicines> getExpiredMeds();


    @Query("select m from Medicines  m where m.manufacturingDate BETWEEN  :startDate AND  :endDate")
    List<Medicines> getMedicinesBetweenMDates(@Param("startDate") LocalDate startDate,
                                              @Param("endDate") LocalDate endDate);

    @Modifying
    @Transactional
    @Query("DELETE from Medicines m where m.expiryDate < CURRENT_DATE AND m.company = :companyName And m.medicinId = :MId")
    int deleteExpiredMedicine(@Param("companyName") String company, @Param("MId") int id);

}