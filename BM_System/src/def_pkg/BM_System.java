package def_pkg;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class BM_System 
{
	
	public static void main(String[] args) 
	{
		JFrame frame=new JFrame("Bank Managment System");
		GUI interf = new GUI();
		Login_Account user = new Login_Account();
	    interf.openSignInForm(frame, user);
		frame.setSize(800,500);  
		frame.setVisible(true);
	}
}
