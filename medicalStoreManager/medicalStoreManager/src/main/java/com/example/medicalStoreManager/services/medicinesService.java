package com.example.medicalStoreManager.services;


import com.example.medicalStoreManager.Entity.Medicines;
import com.example.medicalStoreManager.dao.medicinesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class medicinesService {
    @Autowired
    private medicinesRepo medicinerepo;


    public void addMedicines()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of medicines ");
        String name = sc.next();

        System.out.println("Enter the price of the medicine in ruppee");
        double price = sc.nextDouble();

        System.out.println("Enter the company name of medicine ");
        String compnayName = sc.next();

        System.out.println("Enter the expiry date (yyyy-mm-dd)");
        LocalDate expiryDate = LocalDate.parse(sc.next());

        System.out.println("Enter the manufacturing date(yyyy-mm-dd)");
        LocalDate manufacturingDate = LocalDate.parse(sc.next());

        Medicines medicine = new Medicines();
        medicine.setName(name);
        medicine.setCompany(compnayName);
        medicine.setPrice(price);
        medicine.setExpiryDate(expiryDate);
        medicine.setManufacturingDate(manufacturingDate);
        try{
            medicinerepo.save(medicine);
            System.out.println("saved user ");
        }catch (Exception e){
            System.err.println("failed to save " + e);
        }

    }

    public void addMultipleMedsWitSameCompany(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the company name of medicine ");
        String compnayName = sc.next();

        System.out.println("Enter the number of medicines you want to add for tis company");
        int count = sc.nextInt();

        while(count>0){
            System.out.println("Enter the name of medicines ");
            String name = sc.next();
            System.out.println("Enter the price of the medicine in ruppee");
            double price = sc.nextDouble();
            System.out.println("Enter the expiry date (yyyy-mm-dd)");
            LocalDate expiryDate = LocalDate.parse(sc.next());

            System.out.println("Enter the manufacturing date(yyyy-mm-dd)");
            LocalDate manufacturingDate = LocalDate.parse(sc.next());

            Medicines medicine = new Medicines();
            medicine.setName(name);
            medicine.setPrice(price);
            medicine.setCompany(compnayName);
            medicine.setPrice(price);
            medicine.setExpiryDate(expiryDate);
            medicine.setManufacturingDate(manufacturingDate);
            try{
                medicinerepo.save(medicine);

            }catch (Exception e){
                System.err.println("failed to save " + e);
            }
            count--;

        }
        System.out.println("All medicine saved");

    }

    public void updatePrice(String medicineName , String CompanyName){
        List<Medicines> medicinesList = medicinerepo.findByNameAndCompany(medicineName,CompanyName);
        Scanner sc = new Scanner(System.in);
        if(!medicinesList.isEmpty()){
            System.out.println("Enter the new prize of medicine");
            int newPrize = sc.nextInt();
            for(Medicines meds : medicinesList){
                meds.setPrice(newPrize);
                try{
                    medicinerepo.save(meds);
                }catch (Exception e){
                    System.err.println("not able to update " + e);
                }
            }
            System.out.println("updated succesfully");
        }else{
            System.out.println("no medicine found");
        }
    }

    public void getAllMedicines(){
        List<Medicines> medList = medicinerepo.getAllMedicines();
        if(!medList.isEmpty()){

            for(Medicines med : medList){
                System.out.println(med.toString());
            }
        }else{
            System.out.println("No medicines found");
        }
    }

    public void getMedsByCompany(String companyName){
        List<Medicines> medList = medicinerepo.findByCompany(companyName);
        if(!medList.isEmpty()){
            for(Medicines med : medList){
                System.out.println(med.toString());
            }
        }else{
            System.out.println("No medicines found");
        }
    }

    public void getExpiredMeds(){
        List<Medicines> medList = medicinerepo.getExpiredMeds();
        if(!medList.isEmpty()){
            for(Medicines med : medList){
                System.out.println(med.toString());
            }
        }else{
            System.out.println("No expired medicines found");
        }
    }

    public void getMedsBetweenYears(){
        LocalDate startDate = LocalDate.parse("2020-01-01");
        LocalDate endDate = LocalDate.parse("2024-12-12");
        List<Medicines> medList = medicinerepo.getMedicinesBetweenMDates(startDate,endDate);
        if(!medList.isEmpty()){
            for(Medicines med : medList){
                System.out.println(med.toString());
            }
        }else{
            System.out.println("No expired medicines found");
        }
    }

    public void deleteExpiredMedicine(String companyName , int medId){
        try{
            int deleteCount = medicinerepo.deleteExpiredMedicine(companyName,medId);
            System.out.println("delete succesfully number of deleted items : "+deleteCount);
        }catch (Exception e){
            System.err.println("error while deleting " + e);
        }
    }

}
