package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
		
		public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		private OnlinePaymentService onlinePaymentService;
		
		public ContractService(OnlinePaymentService onlinePaymentService) {
			this.onlinePaymentService = onlinePaymentService;
		}

		public void processContract(Contract contract, Integer months)
		{
			Date instDate;
			List<Installment> installments = new ArrayList<>();
			
			double installmentValue = contract.getTotalValue() / months;
			
			for(int i = 1; i <= months; i++)
			{
			   Calendar c = Calendar.getInstance();
			   c.setTime(contract.getDate());
			   c.add(Calendar.MONTH, i);
			   instDate = c.getTime();
			   
			   double interest = installmentValue + onlinePaymentService.interest(installmentValue, i);
			   double installmentPayment = interest + onlinePaymentService.paymentFee(interest);
			   
			   installments.add(new Installment(instDate, installmentPayment) );
			}
			
			contract.setInstallments(installments);
		}
}
