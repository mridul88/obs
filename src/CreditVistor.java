import java.util.List;

public class CreditVistor implements Visitor
{

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
		if((balance=db.updateBalance(checkingAccount.getAccountNo(), checkingAccount.getAmount()+balance, 2))!=-1)
		{
			checkingAccount.setBalance(balance);
		}
	}

	@Override
	public void visit(SavingsAccount savingsAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(savingsAccount.getAccountNo(),1);
		savingsAccount.setBalance(db.updateBalance(savingsAccount.getAccountNo(), savingsAccount.getAmount()+balance, 1));
	}

	@Override
	public void visit(VisaAccount visaAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(visaAccount.getAccountNo(),3);
		if((balance=db.updateBalance(visaAccount.getAccountNo(), visaAccount.getAmount()+balance, 3))!=-1)
		{
			visaAccount.setBalance(balance);
		}
	}

	@Override
	public void visit(MasterAccount masterAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(masterAccount.getAccountNo(),4);
		if((balance=db.updateBalance(masterAccount.getAccountNo(), masterAccount.getAmount()+balance, 4))!=-1)
		{
			masterAccount.setBalance(balance);
		}
	}

}
