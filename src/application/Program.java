package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

/* 
 * Calculates a contract installments based on the interest 
 * and payment fee of the payment service 
 */

public class Program {
		
	public static void main(String[] args)
	{
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Integer number;
		Date dueDate = null;
		Double totalValue;
		Integer months;
		
		List <Installment> installments;
		
		System.out.println("Enter contract");
		System.out.print("Number: ");
		number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		
		try {
			
			dueDate = sdf.parse(sc.next());
		
		} catch (ParseException e) {
			
			System.out.println("You inserted a wrong date format");
		}
		
		System.out.print("Contract value: ");
		totalValue = sc.nextDouble();
		System.out.print("Enter number of installments: ");
		months = sc.nextInt();
		
		Contract contract = new Contract(number, dueDate, totalValue);
		
		ContractService contractService = new ContractService(new PaypalService() );
		
		contractService.processContract(contract, months);
		
		System.out.println("Installments: ");
		
		installments = contract.getInstallments();
		
		for(Installment inst : installments)
		{
		   System.out.println(inst);
		}
		sc.close();
	}
}
