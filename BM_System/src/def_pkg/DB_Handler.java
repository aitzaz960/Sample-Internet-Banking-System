package def_pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Handler 
{
	
	private String url = "jdbc:mysql://localhost:3306/myschema";       
	private String username = "root";
	private String password = "Nuisb#$419";
	private Connection conn;
	
	public DB_Handler() 
	{    
        try 
        {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully!");
        }
        catch (SQLException e) 
        {
            throw new IllegalStateException("Unable to connect to the database. " + e.getMessage());
        } 
	}
	
	public void getUserInfo(Login_Account user) 
	{
		try 
		{	
			// Check if client already exists
			String uaQuery = "Select * From myschema.Login_Account Where username = \"" + user.getUserName() + "\" and password =\"" + user.getPassword() + "\"";
			System.out.println(uaQuery);
			Statement uaSt = conn.createStatement();
			ResultSet uaRs = uaSt.executeQuery(uaQuery);
	        if( uaRs.next() ) 
	        {
	        	user.setClientId( uaRs.getInt("client_id") );
	        	user.setUserName( uaRs.getString("username") );
	        	user.setAccType( uaRs.getString("type"));
	        	System.out.print(uaRs.getString("type") );
	        }
		}
		catch (SQLException e) 
		{
			System.out.println("Something went wrong while logging in");
		}
	}
	
	public int DB_CreateAccount( Client new_client, String aType ) 
	{
		try 
		{
			// Check if client already exists
			String dQuery = "Select * From myschema.client Where cnic = \""+new_client.getCNIC()+"\"";
			Statement dSt = conn.createStatement();
			ResultSet dRs = dSt.executeQuery(dQuery);
	        int dRecord = 0;
	        while( dRs.next() )
	        	dRecord++;
			if( dRecord < 1 ) 
			{
				System.out.println("No duplicate record");
				
				// Insert new client
				String ciQuery = "Insert Into myschema.client Values(NULL, \""+new_client.getFName()+"\", \""+
						new_client.getLName()+"\", \""+new_client.getFatherName()+"\", \""+
						new_client.getMotherName()+"\", \""+new_client.getCNIC()+"\", STR_TO_DATE(\""+
						new_client.getDOB()+"\", \"%d,%m,%Y\"), \""+
						new_client.getPhone()+"\", \""+new_client.getEmail()+"\", \""+new_client.getAddress()+"\")";
				System.out.println("SQL-> "+ciQuery);
				Statement ciSt = conn.createStatement();
		        ciSt.executeUpdate(ciQuery);
		        
		        // Find the client id
				String cQuery = "Select * From myschema.client Where cnic = \""+new_client.getCNIC()+"\"";
				Statement cSt = conn.createStatement();
				ResultSet cRs = cSt.executeQuery(cQuery);
				if( cRs.next() ) 
				{
					System.out.println("Client was added "+cRs.getString("client_id"));
					int c_id = cRs.getInt("client_id");
					// Make client's bank account
					String baQuery = "Insert Into myschema.bank_account Values(NULL, "+String.valueOf(c_id)+",\""+
							aType+"\", 0, 1, CURDATE(), NULL)";
					System.out.println("SQL-> "+baQuery);
					Statement baSt = conn.createStatement();
			        baSt.executeUpdate(baQuery);
			        
			        return 1;
				}   
			}
		}
		catch (SQLException e) 
		{
			System.out.println("Something went wrong");
		}
		return 0;
	}
	
	public int TransferMoney(int client_id, int rAccNum, int amount) 
	{
		try 
		{	
			// Check if client already exists
			String uaQuery = "Select acc_num, balance From myschema.bank_account Where acc_num = "+String.valueOf(rAccNum);
			System.out.println(uaQuery);
			Statement uaSt = conn.createStatement();
			ResultSet uaRs = uaSt.executeQuery(uaQuery);
	        if( uaRs.next() ) 
	        {
	        	int recv_balance = uaRs.getInt("balance");
	        	String bQuery = "Select balance From myschema.bank_account Where client_id = "+String.valueOf(client_id);
				System.out.println(bQuery);
				Statement bSt = conn.createStatement();
				ResultSet bRs = uaSt.executeQuery(bQuery);
				if( bRs.next() ) 
				{
					int snd_balance = bRs.getInt("balance");
					if( snd_balance >= amount ) {
						snd_balance -= amount;
						recv_balance += amount;
						
						String usQuery = "Update myschema.bank_account Set balance = "+snd_balance+" where client_id="+client_id;		
						System.out.println("SQL-> "+usQuery);
						Statement usSt = conn.createStatement();
				        usSt.executeUpdate(usQuery);
				        
				        String urQuery = "Update myschema.bank_account Set balance = "+recv_balance+" where acc_num="+rAccNum;	
						System.out.println("SQL-> "+urQuery);
						Statement urSt = conn.createStatement();
				        urSt.executeUpdate(urQuery);
				        
				        return 3;
					}
					else
						return 2;
				}
	        }
	        else
	        	return 1;
		}
		catch (SQLException e) 
		{
			System.out.println("Something went wrong while logging in");
		}
		
		return 0;
	}
	
	protected void finalize() 
	{
        try 
        {   
        	System.out.println("Connection Closed");
            conn.close();
        }
        catch (SQLException e) 
        {
            throw new IllegalStateException("Trying to close a not opened db connection" + e.getMessage());
        }
	}
}
