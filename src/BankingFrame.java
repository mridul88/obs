import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankingFrame extends JFrame{
	
	ButtonGroup bg;
	
	private JRadioButton checkingAccountOption;
	private JRadioButton visaAccountOption;
	JRadioButton masterAccountOption;
	JRadioButton savingsAccountOption;
	JRadioButton cashAccountOption;
	JRadioButton creditAccountOption;
	JTextField amountTxt;
	JLabel amountLabel;
	JTextArea log = new JTextArea("LOG",13, 110);
	
	JButton getBalance;
	JButton debit;
	JButton credit;
	
	Dialog dialog;
	private DatabaseController db=DatabaseController.getInstance();
	
	Account cashAccount, creditAccount, savingsAccount, visaAccount, masterAccount,account;
	CheckingAccount checkingAccount;
	
	public BankingFrame(String user, int invokeFrom)
	{
		System.out.println("In Banking frame");
		
		int userId = db.getUserId(user);
		System.out.println("User ID : "+userId);
		
        if(userId != 0)
		{
			// Add all 4 account 
			if(invokeFrom == Constants.SIGNUP)
			{
				cashAccount =  new CashAccount();
				creditAccount = new CreditAccount();
				savingsAccount=new SavingsAccount(userId);
				checkingAccount=new CheckingAccount(userId);
				masterAccount=new MasterAccount(userId);
				visaAccount=new VisaAccount(userId);
				cashAccount.addAccount(savingsAccount);
				cashAccount.addAccount(checkingAccount);
				
				creditAccount.addAccount(masterAccount);
				creditAccount.addAccount(visaAccount);
			}
			else if(invokeFrom == Constants.LOGIN)
			{
				cashAccount =  new CashAccount();
				creditAccount = new CreditAccount();
				savingsAccount=new SavingsAccount(userId);
				checkingAccount=new CheckingAccount(userId);
				masterAccount=new MasterAccount(userId);
				visaAccount=new VisaAccount(userId);
				cashAccount.addAccount(savingsAccount);
				cashAccount.addAccount(checkingAccount);
				
				creditAccount.addAccount(masterAccount);
				creditAccount.addAccount(visaAccount);
			}
		}
		drawFrame(user);
	}
	
	private void drawFrame(String user)
	{
		JLabel welcome;
		JPanel mainPanel;
		JPanel headerPanel, middlePanel, bottomPanel;
		JPanel leftPanel, rightPanel, centerPanel;
		
		
		welcome=new JLabel("<html>Welcome " + user + "<br>\nUser logged in</html>");
		mainPanel = new JPanel(new GridLayout(3,0));
		headerPanel=new JPanel();
		middlePanel = new JPanel();
		bottomPanel = new JPanel();
		
		leftPanel=new JPanel(new GridLayout(3,0));
		rightPanel = new JPanel(new GridLayout(5,0));
		centerPanel = new JPanel(new GridLayout(3,0));
		
		mainPanel.add(headerPanel);
		headerPanel.setAlignmentY(TOP_ALIGNMENT);
		mainPanel.add(middlePanel);
		middlePanel.setAlignmentY(CENTER_ALIGNMENT);
		mainPanel.add(bottomPanel);
		bottomPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		bottomPanel.add(log);
		
		// Setting Properties of Middle Panel
		middlePanel.add(leftPanel);
		leftPanel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setPreferredSize(new Dimension(400,200));
		
		middlePanel.add(centerPanel);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		centerPanel.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setPreferredSize(new Dimension(400,200));
		
		
		middlePanel.add(rightPanel);
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		rightPanel.setAlignmentX(RIGHT_ALIGNMENT);
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setPreferredSize(new Dimension(400,200));
		
		headerPanel.add(welcome);
		welcome.setAlignmentX(TOP_ALIGNMENT);
		
		amountTxt = new JTextField(25);
		amountLabel = new JLabel("Enter the amount :");
		
		// Creating Radio Buttons for Accounts
		cashAccountOption=new JRadioButton("Cash Account");
		checkingAccountOption=new JRadioButton("Checking Account");
		savingsAccountOption = new JRadioButton("Savings Account");
		creditAccountOption=new JRadioButton("Credit Account");
		masterAccountOption=new JRadioButton("Master Account");
		visaAccountOption=new JRadioButton("Visa Account");
		
		// Adding Button to group
		bg = new ButtonGroup();
		bg.add(cashAccountOption);
		bg.add(savingsAccountOption);
		bg.add(checkingAccountOption);
		bg.add(creditAccountOption);
		bg.add(masterAccountOption);
		bg.add(visaAccountOption);
		
		// Buttons for actions
		getBalance = new JButton("Balance");
		getBalance.addActionListener(new getBalanceListener());
		credit = new JButton("Deposit");
		credit.addActionListener(new creditListener());
		debit = new JButton("Witdraw");
		debit.addActionListener(new debitListener());
		
		// Adding elements to the Panel
		leftPanel.add(cashAccountOption);
		centerPanel.add(creditAccountOption);
		centerPanel.add(masterAccountOption);
		centerPanel.add(visaAccountOption);
		leftPanel.add(savingsAccountOption);
		leftPanel.add(checkingAccountOption);
		

		rightPanel.add(getBalance);
		rightPanel.add(debit);
		rightPanel.add(credit);
		rightPanel.add(amountLabel);  
		rightPanel.add(amountTxt);
		
		this.setSize(1000,1000);  
        this.getContentPane().add(mainPanel);
        this.setTitle(new String("Login"));
        this.setVisible(true);
	}
	
	/* yet to be done 26 nov 3:30pm*/
	public class getBalanceListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev)
        {  
        	if(checkingAccountOption.isSelected())
        	{
	        	BalanceVisitor checkingBalanceVisitor=new BalanceVisitor();
	        	checkingAccount.accept(checkingBalanceVisitor);
	        	int balance=checkingAccount.getBalance();
	        	JOptionPane.showMessageDialog(BankingFrame.this, "Checking Account Balance is : " + balance);
	        	log.append("\nChecking Account Balance is : " + balance);
	        	System.out.println("Balance in Banking Frame :"+balance);
        	}
        	else if(cashAccountOption.isSelected())
        	{
        		BalanceVisitor cashBalanceVisitor=new BalanceVisitor();
            	cashAccount.accept(cashBalanceVisitor);
            	int balance=((CashAccount)cashAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Cash Account Balance is : " + balance);
            	log.append("\nCash Account Balance is : " + balance);
            	System.out.println("Cash Balance in Banking Frame :"+balance);
        	}
        	else if (savingsAccountOption.isSelected())
        	{
        		BalanceVisitor savingsBalanceVisitor=new BalanceVisitor();
            	savingsAccount.accept(savingsBalanceVisitor);
            	int balance=((SavingsAccount)savingsAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Savings Account Balance is : " + balance);
            	log.append("\nSavings Account Balance is : " + balance);
            	System.out.println("Savings Balance in Banking Frame :"+balance);
        	}
        	else if (visaAccountOption.isSelected())
        	{
        		BalanceVisitor visaBalanceVisitor=new BalanceVisitor();
            	visaAccount.accept(visaBalanceVisitor);
            	int balance=((VisaAccount)visaAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Visa Account Balance is : " + balance);
            	log.append("\nVisa Account Balance is : " + balance);
            	System.out.println("Visa Balance in Banking Frame :"+balance);
        	}
        	else if(masterAccountOption.isSelected())
        	{
        		BalanceVisitor masterBalanceVisitor=new BalanceVisitor();
            	masterAccount.accept(masterBalanceVisitor);
            	int balance=((MasterAccount)masterAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Master Account Balance is : " + balance);
            	log.append("\nMaster Account Balance is : " + balance);
            	System.out.println("Master Balance in Banking Frame :"+balance);
        		
        	}
        	else if(creditAccountOption.isSelected())
        	{
        		BalanceVisitor creditBalanceVisitor=new BalanceVisitor();
            	creditAccount.accept(creditBalanceVisitor);
            	int balance=((CreditAccount)creditAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Credit Account Balance is : " + balance);
            	log.append("\nCredit Account Balance is : " + balance);
            	System.out.println("Credit Balance in Banking Frame :"+balance);
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(BankingFrame.this, "Please select an account type");
        	}
        }     
    } 
  
	/* yet to be done 26 nov 3:30pm*/
	public class debitListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev)
        {  
        	if(checkingAccountOption.isSelected())
        	{
	        	int amount=Integer.valueOf(amountTxt.getText());
	        	DebitVisitor debitVisitor=new DebitVisitor();
	        	((CheckingAccount)checkingAccount).setAmount(amount);
	        	checkingAccount.accept(debitVisitor);
	        	int balance=((CheckingAccount)checkingAccount).getBalance();
	        	JOptionPane.showMessageDialog(BankingFrame.this, "Checking Account Balance after debit is : " + balance);
	        	System.out.println("Balance in Banking Frame :"+balance);
	        	log.append("\nChecking Account Balance after debit of "+ amount + " is : " + balance);
        	}
        	else if(savingsAccountOption.isSelected())
        	{
        		int amount=Integer.valueOf(amountTxt.getText());
            	DebitVisitor debitVisitor=new DebitVisitor();
            	((SavingsAccount)savingsAccount).setAmount(amount);
            	savingsAccount.accept(debitVisitor);
            	int balance=((SavingsAccount)savingsAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Savings Account Balance after debit is : " + balance);
            	System.out.println("Balance in Banking Frame :"+balance);
            	log.append("\nSavings Account Balance after debit of "+ amount + " is : " + balance);
        	}
        	else if(visaAccountOption.isSelected())
        	{
        		int amount=Integer.valueOf(amountTxt.getText());
            	DebitVisitor debitVisitor=new DebitVisitor();
            	((VisaAccount)visaAccount).setAmount(amount);
            	visaAccount.accept(debitVisitor);
            	int balance=((VisaAccount)visaAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Visa Account Balance after debit is : " + balance);
            	System.out.println("Balance in Banking Frame :"+balance);
            	log.append("\nVisa Account Balance after debit of "+ amount + " is : " + balance);
        	}
        	else if(masterAccountOption.isSelected())
        	{
        		int amount=Integer.valueOf(amountTxt.getText());
            	DebitVisitor debitVisitor=new DebitVisitor();
            	((MasterAccount)masterAccount).setAmount(amount);
            	masterAccount.accept(debitVisitor);
            	int balance=((MasterAccount)masterAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Master Account Balance after debit is : " + balance);
            	System.out.println("Balance in Banking Frame :"+balance);
            	log.append("\nMaster Account Balance after debit of "+ amount + " is : " + balance);
        	}
        	else 
        	{
        		JOptionPane.showMessageDialog(BankingFrame.this, "Please select an account type");
        	}
        	 
        }     
    } 
	/* yet to be done 26 nov 3:30pm*/
	public class creditListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev)
        {  
        	if(checkingAccountOption.isSelected())
        	{
	        	int amount=Integer.valueOf(amountTxt.getText());
	        	CreditVistor creditVisitor=new CreditVistor();
	        	((CheckingAccount)checkingAccount).setAmount(amount);
	        	checkingAccount.accept(creditVisitor);
	        	int balance=((CheckingAccount)checkingAccount).getBalance();
	        	JOptionPane.showMessageDialog(BankingFrame.this, "Checking Account Balance after credit is : " + balance);
	        	System.out.println("Balance in Banking Frame :"+balance);
	        	log.append("\nChecking Account Balance after credit of "+ amount + " is : " + balance);
        	}
        	else if(savingsAccountOption.isSelected())
        	{
        		int amount=Integer.valueOf(amountTxt.getText());
            	CreditVistor creditVisitor=new CreditVistor();
            	((SavingsAccount)savingsAccount).setAmount(amount);
            	savingsAccount.accept(creditVisitor);
            	int balance=((SavingsAccount)savingsAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Savings Account Balance after credit is : " + balance);
            	System.out.println("Balance in Banking Frame :"+balance);
            	log.append("\nSavings Account Balance after credit of "+ amount + " is : " + balance);
        	}
        	else if(visaAccountOption.isSelected())
        	{
        		int amount=Integer.valueOf(amountTxt.getText());
            	CreditVistor creditVisitor=new CreditVistor();
            	((VisaAccount)visaAccount).setAmount(amount);
            	visaAccount.accept(creditVisitor);
            	int balance=((VisaAccount)visaAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Visa Account Balance after credit is : " + balance);
            	System.out.println("Balance in Banking Frame :"+balance);
            	log.append("\nVisa Account Balance after credit of "+ amount + " is : " + balance);
        	}
        	else if(masterAccountOption.isSelected())
        	{
        		int amount=Integer.valueOf(amountTxt.getText());
            	CreditVistor creditVisitor=new CreditVistor();
            	((MasterAccount)masterAccount).setAmount(amount);
            	masterAccount.accept(creditVisitor);
            	int balance=((MasterAccount)masterAccount).getBalance();
            	JOptionPane.showMessageDialog(BankingFrame.this, "Master Account Balance after credit is : " + balance);
            	System.out.println("Balance in Banking Frame :"+balance);
            	log.append("\nMaster Account Balance after credit of "+ amount + " is : " + balance);
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(BankingFrame.this, "Please select an account type");
        	}
        }     
    } 

}
