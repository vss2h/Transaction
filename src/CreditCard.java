//package edu.mtsu.csci3033;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//DO NOT MODIFY THIS FILE
public class CreditCard {

	private Customer customer;
	private String cardBillingAddress;
	private String nameOnCard;
	private double transactionAmount;
	private double purchaseAmount;
	private double donationAmount;
	private String cardNumber;
	private String cardExpires;
	private String cvvCode;
	private String billingZipCode;
	private String transactionDate;
	
	protected CreditCard(Customer customer, double purchaseAmount, double donationAmount) {
	    if (customer == null || customer.custID.isEmpty()) {  
	        throw new NullPointerException("Customer must not be null");  
	    }  
	    if (purchaseAmount <= 0) {  
	        throw new NullPointerException("Invalid Purchase Amount");  
	    }  
		this.customer = customer;
		this.nameOnCard = "";
		this.cvvCode = "";
		this.billingZipCode = "";
		this.purchaseAmount = purchaseAmount;
		this.donationAmount = donationAmount;
		this.transactionAmount = this.purchaseAmount + this.donationAmount;
		this.cardBillingAddress = customer.address + "," + customer.city + "," + customer.ST + "," + customer.zipCode;
	}

	public void setNameOnCard(String name) {
		this.nameOnCard = name;
	}
	
	public String getNameOnCard() {
		return this.nameOnCard;
	}
	
	public void setTransactionAmount(double purchaseAmount, double donationAmount) {
		this.purchaseAmount = purchaseAmount;
		this.donationAmount = donationAmount;
		this.transactionAmount = this.purchaseAmount + this.donationAmount;
	}
	
	public double getTransactionAmount() {
		return this.transactionAmount;
	}
	
	protected void setCardNumber(String card) {
		if (!card.matches("[0-9]+") || card.length() < 13 || card.length() > 19) {
			throw new NullPointerException("Invalid Card Number!");  
		}
		this.cardNumber = card;
	}
	
	protected String getCardNumber() {
		int cardLength = this.cardNumber.length();
		return this.cardNumber.substring(cardLength-4);
	}
	
	public String getCardBillingAddress() {
		return this.cardBillingAddress;
	}
	
	public void setCardBillingAddress(String address, String city, String state, String zip) {
		this.cardBillingAddress = address + "," + city + "," + state + "," + zip ;
	}
	
	public String getCustomerLastName() {
		return this.customer.lastName;
	}
	
	public String getCustomerFirstName() {
		return this.customer.firstName;
	}
	public String getCustomerShippingAddress() {
		return this.customer.address + "," + this.customer.city + "," + this.customer.ST + "," + this.customer.zipCode;
	}
	
	protected void setCVV(String cvv) {
		if ( !cvv.matches("[0-9]+") || cvv.length() < 3 || cvv.length() > 5) {
			throw new NullPointerException("Invalid CVV code");  
		}
		this.cvvCode = cvv;
	}
	
	public String getCardExpiration(){
		return this.cardExpires;
	}
	
	protected void setCardExpiration(String expires) {
		if ( !expires.matches("[0-9/]+") || expires.length() != 5 || !Character.isDigit(expires.charAt(0)) || 
				!Character.isDigit(expires.charAt(1)) || expires.charAt(2) != '/' || !Character.isDigit(expires.charAt(3)) || 
				!Character.isDigit(expires.charAt(4)) ) {
			throw new NullPointerException("Invalid card expiration (format: MM/YY)");  
		}
		this.cardExpires = expires;
	}
	
	protected void setBillingZipCode(String zip) {
		if ( !zip.matches("[0-9]+") || zip.length() != 5 ) {
			throw new NullPointerException("Invalid Zip");  
		}
		this.billingZipCode = zip;
	}
	
	private void setTransactionDate(){
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.transactionDate = dateFormat.format(date);
	}
	
	protected String getTransactionDate(){
		return this.transactionDate;
	}
	
	protected String processTransaction(){
		if (this.nameOnCard.isEmpty() || this.transactionAmount <= 0 || (this.cvvCode == null && this.billingZipCode == null) ) {
			throw new NullPointerException("Invalid Transaction Data!");  
		}
		if (!this.cardNumber.matches("[0-9]+") || this.cardNumber.length() < 13 || this.cardNumber.length() > 19) {
			throw new NullPointerException("Invalid Card Number!");  
		}
		if ( (!this.cvvCode.matches("[0-9]+") || this.cvvCode.length() < 3 || this.cvvCode.length() > 5) && (!this.billingZipCode.matches("[0-9]+") || this.billingZipCode.length() != 5) ) {
			throw new NullPointerException("Zip or CVV must be entered");  
		}
		if (this.customer.custID.isEmpty()) {
			throw new NullPointerException("Unknown Customer ID");  
		}
		
		this.setTransactionDate();
		String code = "";
		for(int i = 0; i <= 5; i++){
			int rand = (int)(Math.random() * 10);
			code = code + rand;
		}
		return this.customer.lastName.substring(0, 3).toUpperCase() + code;
	}
}

