/**
 * 
 */
package com.lti.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lti.bean.Payment;

/**
 * @author 10710128
 *
 */
public class PaymentService implements PaymentInterfaceOperation {

	@Override
	public void showPaymentMethods(Payment payment) {
		// TODO Auto-generated method stub
		List<String> paymentMethods = payment.getPaymentMethods();
		
		System.out.println("\nAvailable payment types: ");
		for(int i=0; i<paymentMethods.size(); i++) {
			System.out.println(i+1 + ". " + paymentMethods.get(i));
		}
	}

	@Override
	public String makePayment() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter payment type: ");
		int paymentMethod = sc.nextInt();
		
		switch(paymentMethod) {
		case 1:
			System.out.println("Please enter valid 12-digit card number:");
			String number = sc.next();
			if (number.length() != 12) {
				System.out.println("Please enter valid 12-digit card number");
			} else {
				System.out.println("->Payment successful<-");
				return "credit card";
			}
			
			break;
		case 2:
			System.out.println("Please enter valid 12-digit card number:");
			String number1 = sc.next();
			if (number1.length() != 12) {
				System.out.println("Please enter valid 12-digit card number");
			} else {
				System.out.println("->Payment successful<-");
				return "debit card";
			}
			break;
		case 3:
			System.out.println("Please enter your valid 11-digit customer id:");
			String number3 = sc.next();
			if (number3.length() != 11) {
				System.out.println("Please enter your valid 11-digit customer id");
			} else {
				System.out.println("->Payment successful<-");
				return "net banking";
			}
			break;
		case 4:
			System.out.println("Please enter your UPI id:");
			String number4 = sc.next();
			
			Pattern pattern = Pattern.compile("^(.+)@(.+)$");
			Matcher matcher = pattern.matcher(number4);
			
			if (!matcher.matches()) {
				System.out.println("Please enter valid UPI id");
			} else {
				System.out.println("->Payment successful<-");
				return "UPI";
			}
			break;
		default:
			System.out.println("->Enter valid payment method<-");
			break;
		}
		
		return "";
	}
	
	
	
}
