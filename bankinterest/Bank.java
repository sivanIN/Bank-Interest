package bankinterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bank {

	public static void main(String[] args) {


		Bank bank = new Bank();
		SavingsAccount savingsAccount=new SavingsAccount();


		String lastName ;
		long PhoneNumber ;

		//		SavingsAccount savingsAccount1 = new SavingsAccount("ram","rao",9807655492l);
		//		savingsAccount.createAccount(savingsAccount1);




		LoanAccount loanAccount = new LoanAccount();
		//		LoanAccount loanAccount1 = new LoanAccount("Rahul","Raj",9807655492l);
		//		loanAccount.createAccount(loanAccount1);

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the day");
		String workingDay = sc.next();

		if(workingDay.equals("monday")  || workingDay.equals("tuesday") || workingDay.equals("wednesday") || workingDay.equals("thursday") || workingDay.equals("friday")  || workingDay.equals("saturday")    ) {
			System.out.println("Welcome to the bank ");
			System.out.println("1.Crete an account");
			System.out.println("2.If you already have an account");
			int accountOpening = sc.nextInt();

			if(accountOpening == 1) {
				System.out.println("1.Create savings account");
				System.out.println("2.Create loan account");
				int typeOfAccounts = sc.nextInt();
				if(typeOfAccounts == 1) {
					System.out.print("Enter First name: ");
					String firstName = sc.next();sc.nextLine();
					System.out.print("Enter Last name: ");
					lastName = sc.next();sc.nextLine();
					System.out.print("Enter Phone number: ");
					PhoneNumber = sc.nextLong();

					SavingsAccount savingsAccounts = new SavingsAccount(firstName,lastName,PhoneNumber);
					SavingsAccount createAccounts = savingsAccount.createAccount(savingsAccounts);

					System.out.println("Savings account created successfully,Account Number is: "+ createAccounts.getAccountNumber());


				}
				else if(typeOfAccounts == 2) {
					System.out.print("Enter First name: ");
					String firstName = sc.next();sc.nextLine();
					System.out.print("Enter Last name: ");
					lastName = sc.next();sc.nextLine();
					System.out.print("Enter Phone number: ");
					PhoneNumber = sc.nextLong();
					LoanAccount loanAccounts = new LoanAccount(firstName,lastName,PhoneNumber);
					LoanAccount createAccount = loanAccount.createAccount(loanAccounts);
					System.out.println("Loan account created successfully,Account Number is: " + createAccount.getAccountNumber());
				}
			}




			System.out.println("Enter Account Number");

			long accountNumber = sc.nextLong();


			int userChoice=0;
			boolean flag = true;

			while(flag) {


				if(savingsAccount.isSavingsAccount(accountNumber) || loanAccount.isLoanAccount(accountNumber)) {
					userChoice = bank.options();
					flag = false;
				}

				else {
					System.out.println("please enter correct number");
					accountNumber = sc.nextLong();
				}
			}

			Aadhaar adhaar = new Aadhaar();
			while(userChoice  > 0) {

				if(userChoice == 2 && savingsAccount.isSavingsAccount(accountNumber)) {


					Aadhaar adhaardetails = bank.enterAdhaarDetails();
					boolean linkAdhaar = savingsAccount.linkAdhaar(adhaardetails,accountNumber); //false

					while(!linkAdhaar) {

						System.out.println("Re enter phone number");
						adhaar.setPhoneNumber(sc.nextLong());

						linkAdhaar = savingsAccount.linkAdhaar(adhaardetails,accountNumber);
					}
					userChoice = bank.options();
					System.out.println(userChoice);

				}else if(userChoice == 2 && loanAccount.isLoanAccount(accountNumber)) {


					Aadhaar enterAdhaarDetails = bank.enterAdhaarDetails();

					boolean linkAdhaar = loanAccount.linkAdhaar(enterAdhaarDetails,accountNumber);

					while(!linkAdhaar) {

						System.out.println("Re enter phone number");
						adhaar.setPhoneNumber(sc.nextLong());

						linkAdhaar = loanAccount.linkAdhaar(enterAdhaarDetails,accountNumber);
					}
					userChoice = bank.options();
				}
				else if(userChoice == 3) {

					System.out.println("Do you have savings account YES/NO");
					String issavingsaccount = sc.next();sc.nextLine();
					if(issavingsaccount.equals("YES")) {
						System.out.println("Enter Account Number");

						accountNumber = sc.nextLong();
						System.out.println("1.withdraw");
						System.out.println("2.Deposit");
						int withdraw_deposit = sc.nextInt();
						if(withdraw_deposit == 1) {
							System.out.println("How much amount to withdraw:" );
							float amount_withdrawn =  sc.nextFloat();
							System.out.println("Enter date of withdraw");sc.nextLine();
							String date = sc.nextLine();

							boolean withdraw = savingsAccount.withdraw(amount_withdrawn,accountNumber,date);

							while(!withdraw) {
								System.out.println("Re enter the amount");
								float amount_withdrawn_re_enter =  sc.nextFloat();
								withdraw = savingsAccount.withdraw(amount_withdrawn_re_enter,accountNumber,date);

							}
							userChoice = bank.options();

						}
						else if(withdraw_deposit == 2){
							System.out.println("How much amount to deposit:" );
							float amount_deposit =  sc.nextFloat();
							System.out.println("Enter date (DD MM YYY)");sc.nextLine();

							String date = sc.nextLine();
							savingsAccount.deposit(amount_deposit, accountNumber,date);
							userChoice = bank.options();

						}
					}else {

						System.out.println("1.Create Savings account");
						System.out.println("2.Go to main menu");
						int loanOption = sc.nextInt();
						if(loanOption == 1) {
							System.out.print("Enter First name: ");
							String firstName = sc.next();sc.nextLine();
							System.out.print("Enter Last name: ");
							lastName = sc.next();sc.nextLine();
							System.out.print("Enter Phone number: ");
							PhoneNumber = sc.nextLong();
							SavingsAccount savingsAccounts = new SavingsAccount(firstName,lastName,PhoneNumber);
							SavingsAccount createAccounts = savingsAccount.createAccount(savingsAccounts);
							System.out.println("Savings Account is created successfully,Accountt number: "+ createAccounts.getAccountNumber());
							userChoice = bank.options();
						}
						else if(loanOption == 2) {
							userChoice = bank.options();
						}

					}

				}
				else if(userChoice == 4  ) {

					System.out.println("Do you have loan account YES/NO");
					String loanAccountavailbe = sc.next();sc.nextLine();
					if(loanAccountavailbe.equals("YES")) {
						System.out.println("Enter loan account number: ");
						accountNumber = sc.nextLong();
						if(loanAccount.isLoanAccount(accountNumber)) {
							System.out.println("1.Borrow");
							System.out.println("2.Repay");
							int borrow_repay = sc.nextInt();

							if(borrow_repay == 1) {
								System.out.println("Enter amount to be borrowed");
								float borrow = sc.nextFloat();
								System.out.println("Enter todays date");sc.nextLine();
								String date  = sc.nextLine();
								loanAccount.borrow(borrow, accountNumber,date);
								userChoice = bank.options();

							}
							else if(borrow_repay == 2) {
								System.out.println("1.cash");
								System.out.println("2.From saving account");
								int mode_of_payment = sc.nextInt();
								if(mode_of_payment == 1) {
									System.out.println("Enter amount to repay");
									float repay =sc.nextFloat();
									System.out.println("Enter todays date");sc.nextLine();
									String date  = sc.nextLine();
									boolean loanRepay = loanAccount.repay(repay, accountNumber,date);

									while(!loanRepay) {
										System.out.println("Re-enter amount to repay");
										repay =sc.nextFloat();
										loanRepay = loanAccount.repay(repay, accountNumber,date);
									}
								}else if(mode_of_payment == 2) {
									System.out.println("Enter Adhaar number: ");
									long adhaarNumber = sc.nextLong();
									SavingsAccount isadhaarlinked = savingsAccount.adhaarlinkedaccounts(adhaarNumber);
									System.out.println(isadhaarlinked);

									if(isadhaarlinked  != null) {
										System.out.println("How much amount pay to loan account:" );
										float pay_loan =  sc.nextFloat();
										System.out.println("Enter date of withdraw");sc.nextLine();
										String date = sc.nextLine();


										boolean withdraw = savingsAccount.withdraw(pay_loan,isadhaarlinked.getAccountNumber(),date);


										while(!withdraw) {
											System.out.println("Re enter the amount");
											float amount_withdrawn_re_enter =  sc.nextFloat();

											withdraw = savingsAccount.withdraw(amount_withdrawn_re_enter,isadhaarlinked.getAccountNumber(),date);
										}

										boolean loanRepay = loanAccount.repay(pay_loan, accountNumber,date);

										while(!loanRepay) {
											System.out.println("Re-enter amount to repay");
											pay_loan =sc.nextFloat();
											loanRepay = loanAccount.repay(pay_loan, accountNumber,date);
										}
										userChoice = bank.options();

									}
									else {
										System.out.println("There is no saving account associated with adhaar number");
										System.out.println("1.Link account with adhaar");
										System.out.println("2.create new saving account");


										int link_create = sc.nextInt();
										if(link_create == 2) {
											System.out.println("Please enter details to create account");
											System.out.print("Enter First name: ");
											String firstName = sc.nextLine();sc.next();
											System.out.print("Enter Last name: ");
											lastName = sc.nextLine();sc.next();
											System.out.print("Enter Phone number: ");
											PhoneNumber = sc.nextLong();
											SavingsAccount savingsAccounts = new SavingsAccount(firstName,lastName,PhoneNumber);
											SavingsAccount createAccounts = savingsAccount.createAccount(savingsAccounts);
											Aadhaar enterAdhaarDetail = bank.enterAdhaarDetails();
											boolean linkAdhaar = savingsAccount.linkAdhaar(enterAdhaarDetail,createAccounts.getAccountNumber()); //false

											while(!linkAdhaar) {
												System.out.println("testcase");
												System.out.println("Re enter phone number");
												adhaar.setPhoneNumber(sc.nextLong());

												linkAdhaar = savingsAccount.linkAdhaar(adhaar,createAccounts.getAccountNumber());

											}
											System.out.println("Deposit amount to repay the loan");
											userChoice = bank.options();
										}
										if(link_create  == 1) {
											Aadhaar enterAdhaarDetail = bank.enterAdhaarDetails();
											boolean linkAdhaar = savingsAccount.linkAdhaar(enterAdhaarDetail,accountNumber); //false

											while(!linkAdhaar) {
												System.out.println("testcase");
												System.out.println("Re enter phone number");
												adhaar.setPhoneNumber(sc.nextLong());

												linkAdhaar = savingsAccount.linkAdhaar(adhaar,accountNumber);
											}
											System.out.println("Deposit amount to repay the loan");
											userChoice = bank.options();
										}

									}
								}
							}
						}}else {
							System.out.println("1.Create loan account");
							System.out.println("2.Go to main menu");
							int loanOption = sc.nextInt();
							if(loanOption == 1) {
								System.out.print("Enter First name: ");
								String firstName = sc.next();sc.nextLine();
								System.out.print("Enter Last name: ");
								lastName = sc.next();sc.nextLine();
								System.out.print("Enter Phone number: ");
								PhoneNumber = sc.nextLong();
								LoanAccount loanAccounts = new LoanAccount(firstName,lastName,PhoneNumber);
								LoanAccount createAccount = loanAccount.createAccount(loanAccounts);
								userChoice = bank.options();
							}
							else if(loanOption == 2) {
								userChoice = bank.options();
							}
						}

				}else if(userChoice == 1  ) {

					if(savingsAccount.isSavingsAccount(accountNumber) ) {
						String printStatment = savingsAccount.PrintStatment(accountNumber);
						System.out.println(printStatment);

						HashMap<Long, List<String>> transaction_details = savingsAccount.transaction_details;

						Iterator<Map.Entry<Long,List<String>>> itr = transaction_details.entrySet().iterator();
						while(itr.hasNext())
						{
							Map.Entry<Long,List<String>> entry = itr.next();
							if(entry.getKey().equals(accountNumber)) {

								List<String> transcationdata_list = entry.getValue();
								if(transcationdata_list.size() == 0) {

									System.out.println("There is no transcation find in this account");
									userChoice = bank.options();
								}else {

									transcationdata_list.forEach(n -> System.out.println(n));
									userChoice = bank.options();}
							}
						}
					}
					else if(loanAccount.isLoanAccount(accountNumber)) {
						String printStatment = loanAccount.PrintStatment(accountNumber);
						System.out.println(printStatment);

						HashMap<Long, List<String>> transaction_details = savingsAccount.transaction_details;

						Iterator<Map.Entry<Long,List<String>>> itr = transaction_details.entrySet().iterator();

						while(itr.hasNext())
						{
							Map.Entry<Long,List<String>> entry = itr.next();
							if(entry.getKey().equals(accountNumber)) {

								List<String> transcationdata_list = entry.getValue();	
								if(transcationdata_list.size() == 0) {
;
									System.out.println("There is no transcation find in this account");
									userChoice = bank.options();
								}else {
									transcationdata_list.forEach(n -> System.out.println(n));
									userChoice = bank.options();
								}
							}
						}
					}userChoice = bank.options();

				}
				else if(userChoice > 4) {
					System.out.println("Please enter valid input");
					userChoice = bank.options();
				}

			}
			System.out.println("Thank you for banking");
		}else {
			System.out.println("Come on working day");}
	}

	public int options() {
		Scanner sc = new Scanner(System.in);

		System.out.println("1.Print statment");
		System.out.println("2.Link Adhaar");
		System.out.println("3.Withdraw/deposit");
		System.out.println("4.Borrow/repay loan");
		System.out.println("0.Exit");
		int userChoice = sc.nextInt();sc.nextLine();
		return userChoice;

	}

	public Aadhaar enterAdhaarDetails() {
		LoanAccount loanAccount = new LoanAccount();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Adhaar Number");
		long adhaarnumber = sc.nextLong();
		System.out.println("Enter first name");
		String firstName = sc.next();sc.nextLine();
		System.out.println("Enter last name: ");
		String lasttName = sc.next();sc.nextLine();
		System.out.println("Enter Phone number");
		long phoneNumber = sc.nextLong();

		Aadhaar adhaardetails = new Aadhaar(adhaarnumber,firstName,lasttName,phoneNumber);



		return adhaardetails;
	}

}
