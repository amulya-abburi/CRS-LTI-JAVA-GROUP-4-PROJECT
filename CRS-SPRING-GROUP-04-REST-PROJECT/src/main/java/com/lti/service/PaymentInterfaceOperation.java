/**
 * 
 */
package com.lti.service;

import com.lti.bean.Payment;

/**interface for payment operations*/
public interface PaymentInterfaceOperation {
	
	/**
	 * method to show all payment methods
	 */
	public void showPaymentMethods(Payment payment);
	
	/**
	 * method to make payment
	 */
	public String makePayment();
}
