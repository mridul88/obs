import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class SignUpFrame extends JFrame {

	JTextField usernameTxt;  
    JPasswordField passwordTxt1;
    JPasswordField passwordTxt2;
	JLabel usernameLbl;
    JLabel passwordLbl1;
    JLabel passwordLbl2;
    JButton createUserButton; 
    JPanel Panel;
    DatabaseController db = DatabaseController.getInstance();
    
    
	public SignUpFrame(){
		
		this.usernameTxt = new JTextField(25);     
        this.passwordTxt1 = new JPasswordField(25);
        this.passwordTxt2 = new JPasswordField(25);
        
		this.usernameLbl = new JLabel("Username: ");  
        this.passwordLbl1 = new JLabel("Password: ");
        this.passwordLbl2 = new JLabel("Re-enter Password: ");
        
        this.createUserButton = new JButton("Create User");  
        
        createUserButton.addActionListener(new createUserButtonListener());
        
        this.Panel = new JPanel();
        this.Panel.add(this.usernameLbl);  
        this.Panel.add(this.usernameTxt);  
        this.Panel.add(this.passwordLbl1);  
        this.Panel.add(this.passwordTxt1);
        this.Panel.add(this.passwordLbl2);  
        this.Panel.add(this.passwordTxt2);
        this.Panel.add(createUserButton);
        
        this.setSize(430,150);  
        this.getContentPane().add(this.Panel,BorderLayout.CENTER);  
        this.setVisible(true);
        
	}
	
  	public class createUserButtonListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev)
        {  
        	int userId;
        	if((userId = db.createUser(usernameTxt.getText(), passwordTxt1.getText()))!=0)
        	{
        		setVisible(false);
        		System.out.println("Create user successful");
        		BankingFrame bankingFrame = new BankingFrame(usernameTxt.getText(), Constants.SIGNUP);
        	}
        	
        }     
    } 
}
