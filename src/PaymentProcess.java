//package edu.mtsu.csci3033;
import java.util.Scanner;
import java.util.InputMismatchException;
/*
 * CSCI3033 Fall18 Project 1
 * Vanessa Serao
 * 
 * Individual Project:
 *   Build and process payment for transaction amount and optional donation
 *   
 *   Once all inputs are received, process transaction through CreditCard class
 */
public class PaymentProcess {
	
	
	public static void main(String[] args) {
		// Passing true to Customer class populates customer info with test data
		Customer customer = new Customer(true);
		double amount = 105.78;
		double donation;
		String cardType= " ";
		//YOUR CODE GOES BELOW THIS LINE
		CreditCard creditcard = new CreditCard(customer, amount, donation=0);
		Scanner scan = new Scanner(System.in);
		
		
		// call for setCardname
		setCardname(creditcard, scan);
		
		// set card type
		cardType= getCardType(scan, cardType);
		
		// request the card number
		setCardnumber(creditcard, scan);
		
		//request the expiration date
		setExpirationDate (creditcard,scan);
	
		// request the Security Code
		setSecurityCode (creditcard,scan);
		
		// request billing address
		setBillingAddress(creditcard,  scan);
		
		//request shipping address
		System.out.println("Shipping address:\n" + "Press 1 if the shipping address is the same as the customer address \n" +
				 "Press 2 if the shipping address is a new address ");
		setShippingAddress( creditcard, customer, scan);
		
		//
		// get donation
		//
		String userAnswer= (scan.nextLine()).toUpperCase();
		System.out.println("Would you like to donate? Yes/No: ");
		userAnswer= (scan.nextLine()).toUpperCase();
		
		if (userAnswer.equals("YES"))
			donation= getDonation(customer, creditcard,  scan, donation, amount);
		else
			System.out.println(" ");	
		
		//
		// print summary
		//
		System.out.println("---------------------------------------------------------------------------\n"
		          +(customer.firstName).toUpperCase() + "," + "Here Is Your Purchase Summary:\n"
				  + "------------------------------------------------------------------------------");
		printSummary( customer, creditcard, donation, cardType, amount,scan);
		
		LastCheck(customer, creditcard, donation, cardType, amount, scan);
		
		scan.close();
	}
	
	private static void setCardname(CreditCard creditcard, Scanner scan)
	{
		// request the name on credit card
		while (true)
		{
			try
			{
				System.out.println("Name on credit card: ");
				String cardName = scan.nextLine();
				creditcard.setNameOnCard(cardName);  
				break;
			}
			catch (NullPointerException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
		}
			
		
	}
	
	private static String getCardType(Scanner scan, String cardType)
	{
		System.out.println("Card Type (MasterCard/Visa): ");
		cardType= (scan.nextLine()).toUpperCase();
		while (!((cardType.equals("MASTERCARD")) || (cardType.equals("VISA"))))
		{
			System.out.println("Invalid card type! Card Type (MasterCard/Visa): ");
			cardType= scan.nextLine().toUpperCase();
		}
		return cardType;
	}
	
	private static void setCardnumber (CreditCard creditcard, Scanner scan)
	{
		while (true)
		{
			try
			{
				
				System.out.println("Card number: ");
				String cardNum= scan.nextLine();
				creditcard.setCardNumber(cardNum);
				break;
			}
			catch (NullPointerException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	private static void setExpirationDate (CreditCard creditcard, Scanner scan)
	{
		while (true)
		{
			try
			{
				System.out.println("Expiration date: ");
				String expirationDate= scan.nextLine();
				creditcard.setCardExpiration(expirationDate);
				break;
			}
			catch (NullPointerException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	private static void setSecurityCode(CreditCard creditcard, Scanner scan)
	{
		while (true)
		{
			try
			{
				System.out.println("Security Code (CVV): ");
				String cvv= scan.nextLine();
				creditcard.setCVV(cvv);
				break;
			}
			catch (NullPointerException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
		}
	}

	private static void setBillingAddress(CreditCard creditcard,  Scanner scan)
	{
		System.out.println("Billing address ");
		
		System.out.println("Address: ");
		String address = scan.nextLine();
		System.out.println("City: ");
		String city = scan.nextLine();
	
	    System.out.println("State: ");
		String state = scan.nextLine();
		
		while (true)
			
		{
			try
			{
				System.out.println("Zip: ");
				String zip= scan.nextLine();
				creditcard.setBillingZipCode(zip);
				creditcard.setCardBillingAddress(address, city, state, zip);
				break;
			}
			
			catch (NullPointerException e)
			{
				System.out.println(e.getMessage());
				continue;
			}
		}
		
		
		
		
		
		
		
				
	}
	
	private static void setShippingAddress(CreditCard creditcard, Customer customer, Scanner scan)
	{
		int answer = scan.nextInt();
		String shippingAddress;
		
		
				//if (answer == 1)
					//shippingAddress = creditcard.getCardBillingAddress();
				if (answer == 1)	
					shippingAddress= customer.charity + " " + customer.address + " " + customer.city + " " + customer.ST + " " + customer.zipCode;
				else if (answer == 2)
				{
					System.out.println ("Enter the new address: ");
					System.out.println("Address: ");
					customer.address = scan.nextLine();
					customer.address = scan.nextLine();
					System.out.println("City: ");
					customer.city = scan.nextLine();
				while(true)
					
				{
					try
					{
						System.out.println("State: ");
						customer.ST = scan.nextLine();
				
					}
					catch (InputMismatchException e)
					{
						System.out.println("Invalid State Name.");
						continue;
					}
				
					
				
					try
					{
						System.out.println("Zip: ");
						customer.zipCode= scan.nextLine();
						shippingAddress= customer.address + " " + customer.city + " " + customer.ST + " " + customer.zipCode;
						break;
					}
					catch (NullPointerException e)
					{
						System.out.println(e.getMessage());
						continue;
					}
					}
				
		}
	}

	private static double getDonation(Customer customer, CreditCard creditcard, Scanner scan, double donation,double amount)
	{
		System.out.println("Enter the amount you would like to donate: ");
		
		String donationAmount= " ";
		while (true)
		{
			try
			{
				donationAmount= scan.nextLine();
				donation= Double.parseDouble(donationAmount);

				while (donation <= 0)
				{
					System.out.println("Sorry! You entered an invalid amount.\nReenter the amount you would like to donate :");
					donationAmount= scan.nextLine();
					donation= Double.parseDouble(donationAmount);
				}
				creditcard.setTransactionAmount(amount, donation);
				return donation;
			}
			
			catch (NumberFormatException e)
			{
	
				System.out.println("Sorry! You entered an invalid amount.\nReenter the amount you would like to donate: ");
				continue;
			}
		}
		
					
					
	}
	
	private static void printSummary(Customer customer, CreditCard creditcard, double donation, String cardType, double amount, Scanner scan)
	{
		
		System.out.println("|Name on credit card: " + creditcard.getNameOnCard() ); 
		System.out.println("|Card Type: " + cardType);
		System.out.println("|Card Number: " + "XXXX-XXXX-" + creditcard.getCardNumber());
		System.out.println("|Expiration date: "+ creditcard.getCardExpiration() );				
		System.out.println("|Billing address: " + creditcard.getCardBillingAddress());					
		System.out.println("|Shipping address: " + creditcard.getCustomerShippingAddress());
		System.out.println("|Donation amount: "+ "$" + donation);
		System.out.println("|Total: $" + creditcard.getTransactionAmount());
		
		
	}
	
	private static void LastCheck(Customer customer, CreditCard creditcard, double donation, String cardType,double amount, Scanner scan)
	{
		System.out.println("\n-----------------------------------------------------------------\n" 
		          +"To Confirm press 1 - " + " To Edit press 2 - "+ " To Cancel press 3"
				  +"\n------------------------------------------------------------------\n");
		int choice= scan.nextInt();
				
				switch(choice)
				{
					case 1: Confirm(customer, creditcard, donation, amount, cardType, scan);
							break;
					case 2: Edit(customer, creditcard, donation, amount,cardType, scan);
							break;
					case 3: Cancel(creditcard);
				}
		
	}
	
	private static void Confirm(Customer customer, CreditCard creditcard,double  donation, double amount, String cardType, Scanner scan)
	{
		
		System.out.println("\n---------------------------------------------------\n" +
							"Your Receipt Is Below.\n" +
							"-----------------------------------------------------\n");
		System.out.println("|Date: "+ creditcard.getTransactionDate() );
		System.out.println("|Transaction code: " + creditcard.processTransaction() );
		printSummary( customer, creditcard, donation, cardType, amount, scan);
		System.out.println("\nThank You For Your Purchase!");
	}
	
	private static void Edit(Customer customer, CreditCard creditcard, double donation, double amount,String cardType, Scanner scan)
	{
		System.out.println("\nWhat would you like to edit?\n");
		System.out.println("Press 1 for the name on the credit card\n"
							+ "Press 2 for the credit card number\n"
							+ "Press 3 for the expiration\n"
							+ "Press 4 for billing address\n"
							+ "Press 5 for shipping address\n"
							+ "Press 6 for the donation amount");
		int choice= scan.nextInt();
	
		switch(choice)
		{
			case 1: scan.nextLine();
					setCardname(creditcard, scan);
					printSummary( customer, creditcard, donation, cardType, amount,scan);
					LastCheck(customer, creditcard, donation, cardType, amount, scan);
					break;
			case 2: scan.nextLine();
					setCardnumber(creditcard, scan);
					printSummary( customer, creditcard, donation, cardType, amount,scan);
					LastCheck(customer, creditcard, donation, cardType, amount, scan);
					break;
			case 3: scan.nextLine();
					setExpirationDate (creditcard,scan);
					printSummary( customer, creditcard, donation, cardType, amount,scan);
					LastCheck(customer, creditcard, donation, cardType, amount, scan);
					break;
			case 4: scan.nextLine();
					setBillingAddress(creditcard,  scan);
					printSummary( customer, creditcard, donation, cardType, amount,scan);
					LastCheck(customer, creditcard, donation, cardType, amount, scan);
					break;
			case 5: scan.nextLine();
					setShippingAddress( creditcard,customer, scan);
					printSummary( customer, creditcard, donation, cardType, amount,scan);
					LastCheck(customer, creditcard, donation, cardType, amount, scan);
					break;
			case 6: scan.nextLine();
					donation= getDonation(customer, creditcard,  scan, donation, amount);
					printSummary( customer, creditcard, donation, cardType, amount,scan);
					LastCheck(customer, creditcard, donation, cardType, amount, scan);
					break;
			default: printSummary( customer, creditcard, donation, cardType, amount,scan);
					 LastCheck(customer, creditcard, donation, cardType, amount, scan);
					 break;
		}
	
	}
	
	private static void Cancel(CreditCard creditcard)
	{
		System.out.println("Your Transaction Has Been Successfully Canceled.");
		return;
	}

}
