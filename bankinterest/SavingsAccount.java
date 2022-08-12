package bankinterest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;



public class SavingsAccount extends Accounts {


	List<SavingsAccount> savingsAccount_list = new ArrayList<>();
	HashMap<Long,SavingsAccount> adhaarlinkedsavingaccount=new HashMap<Long,SavingsAccount>();
	List<String> deposit_date_list = new ArrayList<>() {{add("");}};
	HashMap<Long,List<String>> transaction_details=new HashMap<Long,List<String>>();

	private double savingsBalance;


	public SavingsAccount() {
		super();
		// TODO Auto-generated constructor stub
	} 


	public SavingsAccount( String firstName, String lastName, long phoneNumber
			) {
		super( firstName, lastName, phoneNumber);
		this.savingsBalance = 0;
	}


	public void deposit(float amount,long accountNumber,String endDate) {

		transactionDetails(amount, accountNumber, endDate);
		int NumberOfDays = 0;

		Iterator<Map.Entry<Long,List<String>>> itr = transaction_details.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<Long,List<String>> entry = itr.next();
			if(entry.getKey().equals(accountNumber)) {
				List<String> transcationdata_list = entry.getValue();		
				NumberOfDays= datedifference(transcationdata_list.get(transcationdata_list.size() - 3),endDate);	
			}
		}

		for(SavingsAccount sa: savingsAccount_list) {

			if(sa.getAccountNumber() == accountNumber) {
				double savingbalance = sa.getSavingsBalance();
				double calculateInterest = calculateInterest(NumberOfDays);
				savingbalance = savingbalance + (savingbalance  * (calculateInterest/100));
				savingbalance =Math.round(savingbalance*100)/100;
				savingbalance += amount;
				sa.setSavingsBalance(savingbalance);
				System.out.println("Balance: " + sa.getSavingsBalance());
			}
		}
	}

	public double calculateInterest(int numberofdays) {

		double numberofmonths = (double)numberofdays /30 ;
		double monthlyInterest = 0.5;
		double interest;
		interest = numberofmonths * monthlyInterest;
		return interest;

	}

	public boolean withdraw(float amount,long accountNumber,String witdrawDate) {
		transactionDetails(amount, accountNumber, witdrawDate);
		int NumberOfDays = 0;

		Iterator<Map.Entry<Long,List<String>>> itr = transaction_details.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<Long,List<String>> entry = itr.next();
			if(entry.getKey().equals(accountNumber)) {
				List<String> transcationdata_list = entry.getValue();		
				NumberOfDays= datedifference(transcationdata_list.get(transcationdata_list.size() - 3),witdrawDate);
				System.out.println(NumberOfDays);


			}


		}
		for(SavingsAccount sa: savingsAccount_list) {
			if(sa.getAccountNumber() == accountNumber) {
				double savingbalance = sa.getSavingsBalance();
				double calculateInterest = calculateInterest(NumberOfDays);	
				savingbalance = savingbalance + (savingbalance  * (calculateInterest/100));
				if(savingbalance >= amount) {

					savingbalance =Math.round(savingbalance*100)/100;
					savingbalance -= amount;
					sa.setSavingsBalance(savingbalance);
					System.out.println("Balance: " + sa.getSavingsBalance());
					return true;
				}
			}
		}
		System.out.println("Insufficient fund");
		return false;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public  SavingsAccount createAccount(SavingsAccount savingsAccount){

		savingsAccount_list.add(savingsAccount);

		SavingsAccount last = savingsAccount_list.get(savingsAccount_list.size() - 1);

		return last;
	}

	public  boolean  isSavingsAccount(long accountNumber) {

		for(SavingsAccount sa: savingsAccount_list) {
			if(accountNumber == sa.getAccountNumber()) {
				return true;
			}
		}
		return false;
	}

	public boolean linkAdhaar(Aadhaar adhaar,long accountNumber) {

		for(SavingsAccount  sa: savingsAccount_list) {
			if( adhaar.getPhoneNumber() == sa.getPhoneNumber()) {
				adhaarlinkedsavingaccount.put(adhaar.getAdhaarNumber(), sa);
				System.out.println(adhaarlinkedsavingaccount);
				System.out.println("Adharr linked successfully");
				return true;
			}
		}
		return false;

	}

	public SavingsAccount adhaarlinkedaccounts(long adhaarNumber) {
		System.out.println(adhaarlinkedsavingaccount);

		Iterator<Map.Entry<Long,SavingsAccount>> itr = adhaarlinkedsavingaccount.entrySet().iterator();

		while(itr.hasNext())
		{
			Map.Entry<Long, SavingsAccount> entry = itr.next();
			if(entry.getKey().equals(adhaarNumber)) {

				return entry.getValue();
			}
		}
		return null;
	}

	public int datedifference(String start_date,String end_date) {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String startDate = start_date;
		String enddate = end_date;
		int date_difference = 0;
		if(startDate != "") {
			try {
				Date date1 = myFormat.parse(startDate);
				Date date2 = myFormat.parse(enddate);
				long diff = date2.getTime() - date1.getTime();

				date_difference = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date_difference;
	}



	public String PrintStatment(long accountNumber) {
		String accountDetails = null ;

		for(SavingsAccount sa: savingsAccount_list) {
			if(accountNumber == sa.getAccountNumber()) {
				accountDetails = sa + "";

			}
		}
		return accountDetails;

	}

	public void transactionDetails(float amount,long accountNumber,String endDate) {

		Iterator<Map.Entry<Long,List<String>>> itr = transaction_details.entrySet().iterator();


		if(transaction_details.containsKey(accountNumber)) {
			while(itr.hasNext())
			{
				Map.Entry<Long,List<String>> entry = itr.next();
				if(entry.getKey().equals(accountNumber)) {
					entry.getValue().add(Float. toString(amount) );
					entry.getValue().add(endDate);
				}
			}
			System.out.println(transaction_details);
		}else {
			deposit_date_list.add(Float. toString(amount) );
			deposit_date_list.add(endDate);
			transaction_details.put(accountNumber, deposit_date_list);

		}

	}

	@Override
	public String toString() {
		return "SavingsAccount [accountNumber=" + getAccountNumber() + ", firstName=" + getFirstName() + ", lastName=" + getLastName()
		+ ", phoneNumber=" + getPhoneNumber() + "savingsBalance=" + savingsBalance +"]";

	}





	//	12456789651134





}
