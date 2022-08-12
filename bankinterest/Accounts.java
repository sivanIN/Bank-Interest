package bankinterest;

public class Accounts {
	
	private static long accountNumbergen = 12456789651134l;
	private long accountNumber;
	private String firstName; 
	private String lastName;
	private long phoneNumber;
	
	public Accounts( String firstName, String lastName, long phoneNumber) {

		this.accountNumber = accountNumbergen++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Accounts [accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + "]";
	} 
	
	
	
}
