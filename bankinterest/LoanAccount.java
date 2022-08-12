package bankinterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoanAccount extends Accounts {

	List<LoanAccount> loanAccount_list = new ArrayList<>();
	HashMap<Long,LoanAccount> adhaarlinkedloanaccount=new HashMap<Long,LoanAccount>();
	
	List<String> loan_transcation_list = new ArrayList<>() {{add("");}};
	SavingsAccount savingsAccount = new SavingsAccount();

	private double loanAmount;

	public LoanAccount(String firstName, String lastName, long phoneNumber) {
		super(firstName, lastName, phoneNumber);
		this.loanAmount = 0;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LoanAccount createAccount(LoanAccount loanAccount){
		loanAccount_list.add(loanAccount);
		System.out.println(loanAccount_list);
		LoanAccount last = loanAccount_list.get(loanAccount_list.size() - 1);
		return last;
	}

	public LoanAccount() {

	}

	public void borrow(float amount,long accountNumber,String borrowDate) {

		savingsAccount.transactionDetails(amount, accountNumber, borrowDate);
		int NumberOfDays = 0;

		Iterator<Map.Entry<Long,List<String>>> itr = savingsAccount.transaction_details.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<Long,List<String>> entry = itr.next();
			if(entry.getKey().equals(accountNumber)) {
				List<String> transcationdata_list = entry.getValue();		
				NumberOfDays= savingsAccount.datedifference(transcationdata_list.get(transcationdata_list.size() - 3),borrowDate);	
			}


		}

		for(LoanAccount la: loanAccount_list) {
			if(la.getAccountNumber() == accountNumber) {
				double loanAmount2 = la.getLoanAmount();
				double calculateInterest = savingsAccount.calculateInterest(NumberOfDays);

				loanAmount2 = loanAmount2 + (loanAmount2  * (calculateInterest/100));
				loanAmount2 =Math.round(loanAmount2*100)/100;
				loanAmount2 += amount;
				la.setLoanAmount(loanAmount2);
				System.out.println("Loan amount : "+ la.getLoanAmount());

			}
		}
	}

	public boolean repay(float amount,long accountNumber,String repaydate) {

		savingsAccount.transactionDetails(amount, accountNumber, repaydate);
		int NumberOfDays = 0;

		Iterator<Map.Entry<Long,List<String>>> itr = savingsAccount.transaction_details.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<Long,List<String>> entry = itr.next();
			if(entry.getKey().equals(accountNumber)) {
				List<String> transcationdata_list = entry.getValue();		
				NumberOfDays= savingsAccount.datedifference(transcationdata_list.get(transcationdata_list.size() - 3),repaydate);	
			}
		}

		for(LoanAccount la: loanAccount_list) {
			if(la.getAccountNumber() == accountNumber) {

				if(la.getLoanAmount() > amount) {
					double loanAmount2 = la.getLoanAmount();
					double calculateInterest = savingsAccount.calculateInterest(NumberOfDays);

					loanAmount2 = loanAmount2 + (loanAmount2  * (calculateInterest/100));
					loanAmount2 =Math.round(loanAmount2*100)/100;
					loanAmount2 -= amount;
					la.setLoanAmount(loanAmount2);
					System.out.println("Loan amount : "+ la.getLoanAmount());
					return true;
				}
			}
		}

		System.out.println("Repayment amount is more than currentloanamount"); 
		return false;
	}

	public  boolean  isLoanAccount(long accountNumber) {
		for(LoanAccount sa: loanAccount_list) {
			if(accountNumber == sa.getAccountNumber()) {
				return true;
			}
		}
		return false;

	}

	public String PrintStatment(long accountNumber) {
		String accountDetails = null ;
		for(LoanAccount sa: loanAccount_list) {
			if(accountNumber == sa.getAccountNumber()) {
				accountDetails = sa + "";
			}
		}
		return  accountDetails;
	}


	public boolean linkAdhaar(Aadhaar adhaar,long accountNumber) {

		for(LoanAccount  la: loanAccount_list) {
			if(adhaar.getPhoneNumber() == la.getPhoneNumber()) {
				adhaarlinkedloanaccount.put(adhaar.getAdhaarNumber(), la);
				System.out.println(adhaarlinkedloanaccount);
				return true;
			}
		}
		return false;
	}



	@Override
	public String toString() {
		return "LoanAccount [accountNumber=" + getAccountNumber() + ", firstName=" + getFirstName() + ", lastName=" + getLastName()
		+ ", phoneNumber=" + getPhoneNumber() + "loanAmount=" + loanAmount +"]";

	}



}
