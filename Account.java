package com.crunchify.controller;

import javax.validation.constraints.Pattern;

public class Account {
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String accountNo;
	
	@Pattern(regexp="[0-9]+\\.[0-9][0-9]")
	private float amount;
	
	@Pattern(regexp="[a-zA-Z]+")
	private String firstName;
	
	@Pattern(regexp="[a-zA-Z]+")
	private String lastName;
	private int age;
	private char gender;
	
	@Pattern(regexp="[0-9]+\\.[0-9][0-9]")
	private float initialAmt;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public float getInitialAmt() {
		return initialAmt;
	}
	public void setInitialAmt(float initialAmt) {
		this.initialAmt = initialAmt;
	}
		
	
	

}

