
public class VisaAccount implements Account{

	DatabaseController db = DatabaseController.getInstance();
	int accountNo, amount;
	private int balance;
	
	public VisaAccount(int userId) {
		// TODO Auto-generated constructor stub
		accountNo = db.getAccountNo(userId,3);
		if(accountNo == 0)
		{
			System.out.println("Account No. is 0");
			createNewAccount(userId);
		}
	}


	private void createNewAccount(int userId) {
		// TODO Auto-generated method stub
		accountNo = Constants.getAssignAccount();
		db.add(accountNo, userId, 0, 3);
		
	}


	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	public int getAccountNo() {
		// TODO Auto-generated method stub
		return accountNo;
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

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

}
