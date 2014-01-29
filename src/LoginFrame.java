import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginFrame extends JFrame {
	JTextField usernameTxt;  
    JPasswordField passwordTxt;  
    JLabel usernameLbl, passwordLbl;  
    JButton signUpButton, loginButton;
    JPanel panel;
    DatabaseController db = DatabaseController.getInstance();   
	
    public LoginFrame(){        
        usernameTxt = new JTextField(25);     
        passwordTxt = new JPasswordField(25);
        
        usernameLbl = new JLabel("Username: ");  
        passwordLbl = new JLabel("Password: "); 
        
        signUpButton = new JButton("SignUp");  
        loginButton = new JButton("Login");  
        loginButton.addActionListener(new loginButtonListener());  
        signUpButton.addActionListener(new signUpButtonListener());
//        
        panel = new JPanel();
        this.panel.add(this.usernameLbl);  
        this.panel.add(this.usernameTxt);  
        this.panel.add(this.passwordLbl);  
        this.panel.add(this.passwordTxt);  
        this.panel.add(this.loginButton);
        this.panel.add(this.signUpButton);
        
        this.setSize(400,150);  
        this.getContentPane().add(this.panel);  
        this.setVisible(true);
    }

    public class loginButtonListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev)
        {  
        	if(db.IsUserVerified(usernameTxt.getText(), passwordTxt.getText()) )
        	{
        		setVisible(false);
        		System.out.println("login succesful");
        		BankingFrame bankingFrame=new BankingFrame(usernameTxt.getText(), Constants.LOGIN);
        	}  
        }
    }  
    
    public class signUpButtonListener implements ActionListener{  
        public void actionPerformed(ActionEvent ev)
        {  
	        		setVisible(false);
	            	SignUpFrame signUpFrame = new SignUpFrame();
        }     
    }  

}

