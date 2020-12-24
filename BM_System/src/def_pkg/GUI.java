package def_pkg;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI 
{
	
	public void openSignInForm(JFrame frame, Login_Account user) 
	{
		JPanel f = new JPanel();
		
		JLabel l_hSignIn = new JLabel("Sign In");
		l_hSignIn.setBounds(350,40,100, 40);
		f.add(l_hSignIn);
		
		JLabel l_UserName = new JLabel("User Name");
		l_UserName.setBounds(200,150,100, 40);
		f.add(l_UserName);
		
		JLabel l_Password = new JLabel("Password");
		l_Password.setBounds(200,200,100, 40);
		f.add(l_Password);
		
		JTextField tf_UserName = new JTextField();
		tf_UserName.setBounds(300,150,180, 25);
		f.add(tf_UserName);
		
		JTextField tf_Password = new JTextField();
		tf_Password.setBounds(300,200,180, 25);
		f.add(tf_Password);
		
		JButton btn_SignIn = new JButton("Sign In");
		btn_SignIn.setBounds(300,250,100, 30);
		f.add(btn_SignIn);
		
		btn_SignIn.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				
				user.setUserName( tf_UserName.getText() );
				user.setPassword( tf_Password.getText() );
				
				DB_Handler db = new DB_Handler();
				db.getUserInfo(user);
				
				if(user.isUserValid()) 
				{
					frame.remove(f);
					frame.repaint();
					frame.validate();
					if( user.isClient() ) 
					{
						openClientMenu(frame, user);
					}
					else 
					{
						openManagerMenu(frame, user);	
					}
				}
				else 
				{
				
				}
			}
		});
		
		f.setLayout(null); 
		frame.setContentPane(f);
		frame.setVisible(true);
	}
	
	void openClientMenu(JFrame frame, Login_Account user) 
	{
		JPanel f = new JPanel();
		
		JButton btn_TransferMoney = new JButton("Transfer Money");
		btn_TransferMoney.setBounds(100,100,200, 30);
		f.add(btn_TransferMoney);
		
		btn_TransferMoney.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				frame.remove(f);
				frame.repaint();
				frame.validate();
			    openTransferMoneyForm(frame, user);
			}
		});
		f.setLayout(null); 
		frame.setContentPane(f);
		frame.setVisible(true);
	}
	
	void openTransferMoneyForm(JFrame frame, Login_Account user) 
	{
		JPanel f = new JPanel();
		
		JLabel l_rAccNum = new JLabel("Reciever Account Number: ");
		l_rAccNum.setBounds(100,100,200, 40);
		f.add(l_rAccNum);
		
		JLabel l_amount = new JLabel("Amount: ");
		l_amount.setBounds(100,150,100, 40);
		f.add(l_amount);
	
		JTextField tf_rAccNum = new JTextField();
		tf_rAccNum.setBounds(300,100,180, 25);
		f.add(tf_rAccNum);
		
		JTextField tf_amount = new JTextField();
		tf_amount.setBounds(300,150,180, 25);
		f.add(tf_amount);
		
		JButton btn_Transfer = new JButton("Transfer");
		btn_Transfer.setBounds(300,250,200, 30);
		f.add(btn_Transfer);
		
		btn_Transfer.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				DB_Handler db = new DB_Handler();
				int r = db.TransferMoney(user.getClientId(), Integer.parseInt( tf_rAccNum.getText() ), Integer.parseInt( tf_amount.getText() ));
				if( r == 1 )
					JOptionPane.showMessageDialog(f, "Reciever account not found");
				else if( r == 2)
					JOptionPane.showMessageDialog(f, "You have low balance");
				else if( r == 3 ) 
				{
					JOptionPane.showMessageDialog(f, "Transaction successful");
					frame.remove(f);
					frame.repaint();
					frame.validate();
				    openClientMenu(frame, user);
				}
				else { }
			}
		});
		f.setLayout(null); 
		frame.setContentPane(f);
		frame.setVisible(true);	
	}
	
	void openManagerMenu(JFrame frame, Login_Account user) 
	{
		JPanel f = new JPanel();
		
		JButton btn_CreateAcc = new JButton("Create");
		btn_CreateAcc.setBounds(100,100,100, 30);
		f.add(btn_CreateAcc);
		
		btn_CreateAcc.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {	
				frame.remove(f);
				frame.repaint();
				frame.validate();
			    openCreateAccountForm(frame, user);
			}
		});
		f.setLayout(null); 
		frame.setContentPane(f);
		frame.setVisible(true);
	}
	
	public void openCreateAccountForm(JFrame frame, Login_Account user) 
	{
		//frame.revalidate();
		JPanel f = new JPanel();
		
		JLabel l_hcreate = new JLabel("Create Account");
		l_hcreate.setBounds(350,40,100, 40);
		f.add(l_hcreate);
		
		JLabel l_fName = new JLabel("First Name: ");
		l_fName.setBounds(100,100,100, 40);
		f.add(l_fName);
		JLabel l_lName = new JLabel("Last Name: ");
		l_lName.setBounds(400,100,100, 40);
		f.add(l_lName);
		JLabel l_fatherName = new JLabel("Father Name: ");
		l_fatherName.setBounds(100,150,100, 40);
		f.add(l_fatherName);
		JLabel l_motherName = new JLabel("Mother Name: ");
		l_motherName.setBounds(400,150,100, 40);
		f.add(l_motherName);
		JLabel l_cnic = new JLabel("CNIC: ");
		l_cnic.setBounds(100,200,100, 40);
		f.add(l_cnic);
		JLabel l_dob = new JLabel("Date of Birth: ");
		l_dob.setBounds(400,200,100, 40);
		f.add(l_dob);
		JLabel l_phone = new JLabel("Phone: ");
		l_phone.setBounds(100,250,100, 40);
		f.add(l_phone);
		JLabel l_email = new JLabel("Email: ");
		l_email.setBounds(400,250,100, 40);
		f.add(l_email);
		JLabel l_address = new JLabel("Address: ");
		l_address.setBounds(100,300,100, 40);
		f.add(l_address);
		JLabel l_acc_type = new JLabel("Account Type: ");
		l_acc_type.setBounds(400,300,100, 40);
		f.add(l_acc_type);
		
		JTextField tf_fName = new JTextField();
		tf_fName.setBounds(200,100,180, 25);
		f.add(tf_fName);
		JTextField tf_lName = new JTextField();
		tf_lName.setBounds(500,100,180, 25);
		f.add(tf_lName);
		JTextField tf_fatherName = new JTextField();
		tf_fatherName.setBounds(200,150,180, 25);
		f.add(tf_fatherName);
		JTextField tf_motherName = new JTextField();
		tf_motherName.setBounds(500,150,180, 25);
		f.add(tf_motherName);
		JTextField tf_cnic = new JTextField();
		tf_cnic.setBounds(200,200,180, 25);
		f.add(tf_cnic);
		JTextField tf_dob = new JTextField();
		tf_dob.setBounds(500,200,180, 25);
		f.add(tf_dob);
		JTextField tf_phone = new JTextField();
		tf_phone.setBounds(200,250,180, 25);
		f.add(tf_phone);
		JTextField tf_email = new JTextField();
		tf_email.setBounds(500,250,180, 25);
		f.add(tf_email);
		JTextField tf_address = new JTextField();
		tf_address.setBounds(200,300,180, 25);
		f.add(tf_address);
		
		String[] types = {"Current", "Saving" };
		JComboBox jcb_types = new JComboBox(types);
		jcb_types.setBounds(500,300,180, 25);
		f.add(jcb_types);
		
		JButton btn_mm = new JButton("Main Menu");
		btn_mm.setBounds(50,400,100, 30);
		f.add(btn_mm);
		
		JButton btn_create = new JButton("Submit");
		btn_create.setBounds(350,365,100, 30);
		f.add(btn_create);
		
		btn_mm.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				frame.remove(f);
				frame.repaint();
				frame.validate();
				openManagerMenu(frame, user);
			}
		});
		
		
		btn_create.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Client new_client = new Client(
						tf_fName.getText(), tf_lName.getText(), tf_fatherName.getText(), tf_motherName.getText(),
						tf_cnic.getText(), tf_dob.getText(), tf_phone.getText(), tf_email.getText(),
						tf_address.getText()
						);
				DB_Handler db = new DB_Handler();
				db.DB_CreateAccount(new_client, jcb_types.getSelectedItem().toString() );
				
				JOptionPane.showMessageDialog(f, "Account created");
				frame.remove(f);
				frame.repaint();
				frame.validate();
			    openManagerMenu(frame, user);			
			}
		});
		f.setLayout(null); 
		frame.setContentPane(f);
		frame.setVisible(true);
	}
}
