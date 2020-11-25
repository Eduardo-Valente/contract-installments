package model.services;

public class PaypalService implements OnlinePaymentService {

	public static final double INTEREST_PERCENT = 0.01;
	public static final double FEE_PERCENT = 0.02;
	
	@Override
	public Double paymentFee(Double amount) {
		
		return amount * FEE_PERCENT;
	}

	@Override
	public Double interest(Double amount, Integer months) {
		
		return amount * INTEREST_PERCENT * months;
	}

	    
}
