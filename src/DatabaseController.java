import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* This is a singleton class, so that our application 
 * will have only one database connection
 * */

public class DatabaseController {
	  Connection connection;
	  Statement stmt;
	  String database;
	  ResultSet resultSet;
	  private static DatabaseController instance;
	  private static int userId = 100000;
	  
	  private DatabaseController()
	  {
	  }
	  
	  public static synchronized DatabaseController getInstance(){
		   if(instance == null)
		   {
			    instance = new DatabaseController();
		   }
		   return instance;
	  }
	  
	  public boolean ConnectDatabase()throws ClassNotFoundException
	  {
		  try 
	        {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				connection = DriverManager.getConnection("jdbc:odbc:ADB");
			    stmt = connection.createStatement();
			    
			    System.out.println("Connection established");
			    return true;
			    
			} 
	        catch (SQLException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	        
	  }
	  
	  
	  public boolean IsUserVerified(String username, String password){
		String query = "SELECT * FROM Login WHERE USERNAME = '"+ username + "' AND PASSWORD = '" + password +"' ";
      	System.out.println(query);
      	try {
				resultSet=stmt.executeQuery(query);
				if(resultSet.next())
				{
					return true;
				}
				else
				{
					System.out.println("ResultSet is empty");
					return false;
				}
      	}
      	catch(Exception e)
      	{
      		e.printStackTrace();
      	}
      	return false;
	  }
	  
	  /* This function will insert the user into  the database and 
	   *  will return the account number*/
	  public int createUser(String username, String password)
	  {
		    getUniqueUserId();
			
		    String query = "INSERT INTO LOGIN(USERID,USERNAME,PASSWORD) VALUES('"+ userId+ "','"+ 
																						username +"','" +password+"')";
        	System.out.println(query);
        	
        	try 
        	{
				stmt.executeUpdate(query);
				System.out.println("Successful Updation");
				return userId++;
            }
        	catch(Exception e)
        	{
        		e.printStackTrace();
        		return 0;
        	}
	  }

	private void getUniqueUserId() 
	{
		// TODO Auto-generated method stub
		String query = "SELECT MAX(USERID) AS MAXID FROM LOGIN";
		System.out.println(query);
		
		try 
		{
			resultSet = stmt.executeQuery(query);
			System.out.println("Successful Updation");
			
			if(resultSet.next())
			{
				int tmp = resultSet.getInt("MAXID");
				
				if(tmp != 0)
				{
					userId =  tmp;
					userId++;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean add(int accountNo, int userId, int balance, int tableType)
	{
		// TODO Auto-generated method stub
		String table_name;
		switch(tableType)
		{
			case 1: table_name = "SAVINGSACCOUNT";
					break;
			case 2: table_name = "CHECKINGACCOUNT";
					break;		
			case 3: table_name = "VISAACCOUNT";
					break;
			case 4: table_name = "MASTERACCOUNT";
					break;
			default: return false;
		}
		
		String query = "INSERT INTO " + table_name + "(USERID,ACCOUNTNO,BALANCE) VALUES('"+ userId+ "','"+ 
				accountNo +"','" +balance+"')";
		System.out.println("Query : "+query);
		
		System.out.println(query);
		
		try 
		{
			stmt.executeUpdate(query);
			System.out.println("Successful Updation");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public int getAccountNo(int userId, int tableType) {
		// TODO Auto-generated method stub
		
		ResultSet resultSet;
		String table_name;
		switch(tableType)
		{
			case 1: table_name = "SAVINGSACCOUNT";
					break;
			case 2: table_name = "CHECKINGACCOUNT";
					break;		
			case 3: table_name = "VISAACCOUNT";
					break;
			case 4: table_name = "MASTERACCOUNT";
					break;
			default: return -1;
		}
		
		String query = "SELECT ACCOUNTNO FROM " + table_name + " WHERE USERID = "+ userId ;
		System.out.println(query);
		
		try 
		{
			resultSet = stmt.executeQuery(query);
			if(resultSet.next())
			{
				int index=resultSet.findColumn("ACCOUNTNO");
				int accountNo = resultSet.getInt(index);
				System.out.println("Successful Updation");
				System.out.println(accountNo);
				return accountNo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
		System.out.println("Reached after try catch getAccountNo");
		return 0;
	}

	public int getBalance(int accountNo, int tableType) {
		// TODO Auto-generated method stub
		ResultSet resultSet;
		String table_name;
		
		switch(tableType)
		{
			case 1: table_name = "SAVINGSACCOUNT";
					break;
			case 2: table_name = "CHECKINGACCOUNT";
					break;		
			case 3: table_name = "VISAACCOUNT";
					break;
			case 4: table_name = "MASTERACCOUNT";
					break;
			default: return -1;
		}
		
		String query = "SELECT BALANCE FROM " + table_name + " WHERE ACCOUNTNO = "+ accountNo ;
		System.out.println(query);
		
		try 
		{
			resultSet = stmt.executeQuery(query);
			if(resultSet.next())
			{
				int index=resultSet.findColumn("BALANCE");
				int balance = resultSet.getInt(index);
				System.out.println("Successful Updation");
				return balance;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
	

	public int updateBalance(int accountNo, int amount, int tableType) {
		// TODO Auto-generated method stub
				ResultSet resultSet;
				String table_name;
				
				switch(tableType)
				{
					case 1: table_name = "SAVINGSACCOUNT";
							break;
					case 2: table_name = "CHECKINGACCOUNT";
							break;		
					case 3: table_name = "VISAACCOUNT";
							break;
					case 4: table_name = "MASTERACCOUNT";
							break;
					default: return -1;
				}
				
				String query = "UPDATE " + table_name + " SET BALANCE ="+ amount +" WHERE ACCOUNTNO = " +accountNo;
				System.out.println(query);
				
				try 
				{
					stmt.executeUpdate(query);
					return amount;
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return -1;
				}
				
	}
	
	public int getUserId(String username)
	{
		String query = "SELECT USERID FROM LOGIN WHERE USERNAME = '"+username+"'";
		ResultSet resultSet;
		try 
		{
			resultSet = stmt.executeQuery(query);
			
			if(resultSet.next())
			{
				int index = resultSet.findColumn("USERID");
				return resultSet.getInt(index);
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	

}
