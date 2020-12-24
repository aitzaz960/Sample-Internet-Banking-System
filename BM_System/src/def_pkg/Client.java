package def_pkg;

public class Client 
{
	private String client_id;
	private String f_name;
	private String l_name;
	private String father_name;
	private String mother_name;
	private String cnic;
	private String dob;
	private String phone;
	private String email;
	private String address;
	
	// Default Constructor
	public Client() 
	{
		client_id = "";
		f_name = "";
		l_name = "";
		father_name = "";
		mother_name = "";
		cnic = "";
		dob = "";
		phone = "";
		email = "";
		address = "";
	}

	// Parameterized Constructor
	public Client(String f_name, String l_name, String father_name, String mother_name, String cnic, String dob, String phone, String email, String address) 
	{
		client_id = "";
		this.f_name = f_name;
		this.l_name = l_name;
		this.father_name = father_name;
		this.mother_name = mother_name;
		this.cnic = cnic;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	
	// Parameterized Constructor
	public Client(String id, String pf_name, String pl_name, String pfather_name, String pmother_name, String pcnic, String pdob, String pphone, String pemail, String paddress) {
		this.client_id = id;
		this.f_name = pf_name;
		this.l_name = pl_name;
		this.father_name = pfather_name;
		this.mother_name = pmother_name;
		this.cnic = pcnic;
		this.dob = pdob;
		this.phone = pphone;
		this.email = pemail;
		this.address = paddress;
	}	
	
	void showClientInfo() 
	{
		System.out.println("First Name: "+f_name);
		System.out.println("Last Name: "+l_name);
		System.out.println("Father Name: "+father_name);
		System.out.println("Mother Name: "+mother_name);
		System.out.println("CNIC Name: "+cnic);
		System.out.println("DOB Name: "+dob);
		System.out.println("Phone Name: "+phone);
		System.out.println("Email Name: "+email);
		System.out.println("Address Name: "+address);
	}
	
	public String getFName() 
	{
		return f_name;
	}
	public String getLName() 
	{
		return l_name;
	}
	public String getFatherName() 
	{
		return father_name;
	}
	public String getMotherName() 
	{
		return mother_name;
	}
	public String getCNIC() 
	{
		return cnic;
	}
	public String getDOB() 
	{
		return dob;
	}
	public String getPhone() 
	{
		return phone;
	}
	public String getEmail() 
	{
		return email;
	}
	public String getAddress() 
	{
		return address;
	}
}
