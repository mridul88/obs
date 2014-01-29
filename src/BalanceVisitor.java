import java.util.List;

public class BalanceVisitor implements Visitor {

	DatabaseController db = DatabaseController.getInstance();
	
	@Override
	public void visit(CashAccount cashAccount) {
		// TODO Auto-generated method stub
		List<Account> list = cashAccount.getAccountList();
		int balance = 0;
		
		for(int i=0;i < list.size(); i++)
		{ 
			if(list.get(i) instanceof SavingsAccount)
			{
				visit(((SavingsAccount)list.get(i)));
				balance += ((SavingsAccount)list.get(i)).getBalance();
			}
			else if(list.get(i) instanceof CheckingAccount)
			{
				visit(((CheckingAccount)list.get(i)));
				balance += ((CheckingAccount)list.get(i)).getBalance();
			}
		}
		
		cashAccount.setBalance(balance);
	}

	@Override
	public void visit(CreditAccount creditAccount) {
		// TODO Auto-generated method stub
		List<Account> list = creditAccount.getAccountList();
		int balance =0;
		
		for(int i=0;i < list.size(); i++)
		{ 
			if(list.get(i) instanceof VisaAccount)
			{
				visit(((VisaAccount)list.get(i)));
				balance += ((VisaAccount)list.get(i)).getBalance();
			}
			else if(list.get(i) instanceof MasterAccount)
			{
				visit(((MasterAccount)list.get(i)));
				balance += ((MasterAccount)list.get(i)).getBalance();
			}
		}
		creditAccount.setBalance(balance);
		
	}

	@Override
	public void visit(CheckingAccount checkingAccount)
	{
		// TODO Auto-generated method stub
		 int balance = db.getBalance(checkingAccount.getAccountNo(),2);
		 checkingAccount.setBalance(balance);
		 System.out.println("Checking Balance :"+balance);
	}

	@Override
	public void visit(SavingsAccount savingsAccount) {
		// TODO Auto-generated method stub
		 int balance = db.getBalance(savingsAccount.getAccountNo(),1);
		 savingsAccount.setBalance(balance);
	}

	@Override
	public void visit(VisaAccount visaAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(visaAccount.getAccountNo(),3);
		visaAccount.setBalance(balance); 
	}

	@Override
	public void visit(MasterAccount masterAccount) {
		// TODO Auto-generated method stub
		int balance = db.getBalance(masterAccount.getAccountNo(),4);
		masterAccount.setBalance(balance); 
	}

}
