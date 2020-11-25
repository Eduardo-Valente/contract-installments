package model.services;


import java.util.Calendar;
import java.util.Date;
import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
		
		private OnlinePaymentService onlinePaymentService;
		
		public ContractService(OnlinePaymentService onlinePaymentService) {
			this.onlinePaymentService = onlinePaymentService;
		}

		public void processContract(Contract contract, Integer months)
		{
			Date instDate;
			
			double installmentValue = contract.getTotalValue() / months;
			
			for(int i = 1; i <= months; i++)
			{
			   instDate = addMonth(contract.getDate(), i);
			   
			   double interest = installmentValue + onlinePaymentService.interest(installmentValue, i);
			   double installmentPayment = interest + onlinePaymentService.paymentFee(interest);
			   
			   contract.addInstallment(new Installment(instDate, installmentPayment) );
			}
		}
		
		public static Date addMonth(Date date, int numberMonth)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, numberMonth);
			return c.getTime();
		}
}
