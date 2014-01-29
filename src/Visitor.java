
public interface Visitor
{
	public void visit(CashAccount cashAccount);
	public void visit(CreditAccount creditAccount);
	public void visit(CheckingAccount checkingAccount);
	public void visit(SavingsAccount savingsAccount);
	public void visit(VisaAccount visaAccount);
	public void visit(MasterAccount masterAccount);

}
