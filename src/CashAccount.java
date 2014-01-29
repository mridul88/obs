import java.util.ArrayList;
import java.util.List;

public class CashAccount implements Account
{
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
	
	public CashAccount()
	{
		;
	}
	
	public CashAccount(int userId)
	{
		CheckingAccount ch = new CheckingAccount(userId);
		SavingsAccount sa = new SavingsAccount(userId);
		accountList.add(ch);
		accountList.add(sa);
	}
	
	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		accountList.add(account);
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
