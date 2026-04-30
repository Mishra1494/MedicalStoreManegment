package com.example.medicalStoreManager;

import com.example.medicalStoreManager.services.medicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MedicalStoreManagerApplication  implements CommandLineRunner {

	@Autowired
	medicinesService medicinesservice;
	public static void main(String[] args) {
		SpringApplication.run(MedicalStoreManagerApplication.class, args);
	}
	public void run(String ...arg){
		Scanner sc = new Scanner(System.in);
		System.out.println("\n===== MEDICAL STORE MANAGEMENT =====");
		System.out.println("1. Add Medicine");
		System.out.println("2. Add Multiple Medicines (Same Company)");
		System.out.println("3. Update Medicine Price");
		System.out.println("4. Show All Medicines");
		System.out.println("5. Find Medicines by Company");
		System.out.println("6. Show Expired Medicines");
		System.out.println("7. Show Medicines Manufactured Between 2020-2024");
		System.out.println("8. Delete Expired Medicine (By Company & ID)");

		System.out.print("Enter your choice: ");
		int op = sc.nextInt();

		switch (op){
			case 1:
				System.out.println("Enter the medicine details to add medicine : ");
				medicinesservice.addMedicines();
				break;
			case 2:
				System.out.println("Enter the medicines details to add medicine : ");
				medicinesservice.addMultipleMedsWitSameCompany();
				break;
			case 3 :
				System.out.println("Enter the detail require to update the prize");
				System.out.println("Enter the name of the medicine");
				String medName = sc.next();
				System.out.println("Enter the name of companay");
				String companyName = sc.next();
				medicinesservice.updatePrice(medName,companyName);
				break;
			case 4:
				System.out.println("All medicins info ; ");
				medicinesservice.getAllMedicines();
				break;
			case 5:
				 System.out.println("Enter the name of company whose medicine detail you want : ");
				 medicinesservice.getMedsByCompany(sc.next());
				 break;
			case 6:
				System.out.println("Following are the expired medicines");
				medicinesservice.getExpiredMeds();
				break;
			case 7:
				System.out.println("following are the medicines whose manufacturing date is in between 2020 to 2024");
				medicinesservice.getMedsBetweenYears();
				break;
			case 8:
				System.out.println("Enter the require detail to delete the expired medicines ");
				System.out.println("Enter the name of company ");
				String company = sc.next();
				System.out.println("Enter the medicine id you want to delete  ");
				int medId = sc.nextInt();
				medicinesservice.deleteExpiredMedicine(company,medId);
				break;
			default :
				System.out.println("Wrong choice entered ");
		}


	}

}
