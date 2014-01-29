import java.util.ArrayList;
import java.util.List;



public class CreditAccount implements Account{

	private List<Account> accountList=new ArrayList<Account>();
	private int amount;
	private int balance; 
	
	public List<Account> getAccountList() {
		return accountList;
	}

	
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		accountList.add(account);
	}

	
	public CreditAccount() {
	// TODO Auto-generated constructor stu
	}
	
	public CreditAccount(int userId)
	{
		VisaAccount ch = new VisaAccount(userId);
		MasterAccount sa = new MasterAccount(userId);
		accountList.add(ch);
		accountList.add(sa);
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public void setBalance(int balance)
	{
		this.balance = balance;
	}
	
	public int getBalance()
	{
		return this.balance;
	}
	

}
