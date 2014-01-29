public class DebitVisitor implements Visitor {

DatabaseController db = DatabaseController.getInstance();
	
@Override
public void visit(CashAccount cashAccount) {
	// TODO Auto-generated method stub
}

@Override
public void visit(CreditAccount creditAccount) {
	// TODO Auto-generated method stub
	
}

	@Override
	public void visit(CheckingAccount checkingAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(checkingAccount.getAccountNo(),2);
		if(balance>=checkingAccount.getAmount())
		{
			if((balance=db.updateBalance(checkingAccount.getAccountNo(), balance-checkingAccount.getAmount(), 2))!=-1)
			{
				checkingAccount.setBalance(balance);
			}
		}
		else
		{
			System.out.println("Low balance in checking account");
		}
	}

	@Override
	public void visit(SavingsAccount savingsAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(savingsAccount.getAccountNo(),1);
		if(balance>=savingsAccount.getAmount())
		{
			if((balance=db.updateBalance(savingsAccount.getAccountNo(), balance - savingsAccount.getAmount(), 1))!=-1)
			{
				savingsAccount.setBalance(balance);
			}
		}
		else
		{
			System.out.println("Low Balance in savings account");
		}
	}

	@Override
	public void visit(VisaAccount visaAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(visaAccount.getAccountNo(),3);
		if(balance>=visaAccount.getAmount())
		{
			if((balance=db.updateBalance(visaAccount.getAccountNo(), balance - visaAccount.getAmount(), 3))!=-1)
			{
				visaAccount.setBalance(balance);
			}
		}
		else
		{
			System.out.println("Low balance in visa account");
		}
	}

	@Override
	public void visit(MasterAccount masterAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(masterAccount.getAccountNo(),4);
		if(balance>=masterAccount.getAmount())
		{
			if((balance=db.updateBalance(masterAccount.getAccountNo(), balance - masterAccount.getAmount(), 4))!=-1)
			{
				masterAccount.setBalance(balance);
			}
		}
		else
		{
			System.out.println("Low balance in master account");
		}
	}

}
