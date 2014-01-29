package singleton;

public class Account 
{
	private static Account instance;
	private Account() {
		// TODO Auto-generated constructor stub
	}

	public static synchronized Account getInstance()
	{
		if (instance == null)
			instance = new Account();

		return instance;
	}

}
