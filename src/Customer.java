//package edu.mtsu.csci3033;

// DO NOT MODIFY THIS FILE
public class Customer {
	public String custID;
	public String firstName;
	public String lastName;
	public String memberSince;
	public String charity;
	public String address;
	public String city;
	public String ST;
	public String zipCode;
	
	public Customer() 
	{}
	
	public Customer(boolean test) {
		if (test) {
			this.custID = "S00001";
			this.firstName = "John";
			this.lastName = "Smith";
			this.memberSince = "2012";
			this.charity = "Homeless_Pets";
			this.address = "123 Main St";
			this.city = "Murfreesboro";
			this.ST = "TN";
			this.zipCode = "12345";
		}
	}
}
