  
public class BankingSystem{  

    public static void main(String[] args) throws ClassNotFoundException{  
    	DatabaseController db = DatabaseController.getInstance();
    	
    	
    	if(db.ConnectDatabase())
    	{
    		LoginFrame loginFrame = new LoginFrame();
    	}
    	else
    	{
    		System.out.println("error in database connection");
    	}
    	
        
    } 
   
}