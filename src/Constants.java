
public class Constants 
{
	private static int assignAccountNo = 10000;
	public static final int LOGIN =0;
	public static final int  SIGNUP = 1;
	
	public static int getAssignAccount()
	{
		return assignAccountNo++; 
	}
}
