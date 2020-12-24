package def_pkg;

public class Login_Account 
{
	int client_id;
	private String userName;
	private String password;
	private String accType;

	public Login_Account() 
	{
		this.client_id = 0;
		this.userName = "";
		this.password = "";
		this.accType = "";
	}
	
	public Login_Account(int c_id, String user, String pass, String aType) 
	{
		this.client_id = c_id;
		this.userName = user;
		this.password = pass;
		this.accType = aType;
	}	
	
	public void setClientId(int id) 
	{
		this.client_id = id;
	}
	
	public void setUserName(String username) 
	{
		this.userName = username;
	}

	public void setPassword(String pass) 
	{
		this.password = pass;
	}

	public void setAccType(String aType) 
	{
		this.accType = aType;
	}
	
	public String getUserName() 
	{
		return this.userName;
	}
	
	public String getPassword() 
	{
		return this.password;
	}
	
	public int getClientId() 
	{
		return this.client_id;
	}
	
	public boolean isClient() 
	{
		if( accType.compareTo("a") == 0 ) 
			return false;
		else
			return true;
	}
	public boolean isUserValid() 
	{
		if( accType == "" ) 
		{
			return false;
		}
		else
			return true;
	}
	public void showUserInfo() 
	{
		System.out.println("User: "+userName+" Type: "+accType);
	}
}
