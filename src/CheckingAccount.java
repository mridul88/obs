public class CheckingAccount implements Account
{
	private DatabaseController db = DatabaseController.getInstance();
	private int accountNo, amount, balance;
	
	public CheckingAccount(int userId) {
		// TODO Auto-generated constructor stub
		
		accountNo = db.getAccountNo(userId,2);
		if(accountNo == 0)
		{
			System.out.println("Account No. is 0");
			createNewAccount(userId);
		}
	
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
	
	public void createNewAccount(int userId)
	{
		accountNo = Constants.getAssignAccount();
		db.add(accountNo, userId, 0, 2);
	}

	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	public int getAccountNo() {
		// TODO Auto-generated method stub
		return accountNo;
	}
	
}