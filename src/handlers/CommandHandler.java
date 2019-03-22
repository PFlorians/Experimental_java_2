package handlers;
import java.awt.event.*;
import javax.swing.*;
import graphics.*;

//use this to handle console commands
public class CommandHandler implements ActionListener{
	private Design1 win;
	public CommandHandler(Object win)
	{
		if(win instanceof Design1)
		{
			this.win=(Design1)win;
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		JFormattedTextField jftf=null;
		
		if(e.getSource() instanceof JFormattedTextField)
		{
			jftf=(JFormattedTextField)e.getSource();
		}
		else
		{
			System.out.println("Error nieco s grafikou nesuhlasi");
		}
		
		if(jftf!=null)
		{
			//pozor na exploit help System.out.println("hack") apod.
			if(jftf.getText().toLowerCase().contains("help"))
			{
				jftf.setText("Console# ");
				this.win.addTxt("\ncopyright Red_Mist\n"
						+ "*** 'I do it because I can' ***\n"
						+ "Cmds: \n"
						+ "		[*] cls - clear screen\n"
						+ "		[*] help - this help\n"
						+ "		[*] scsub - scan subnet\n"
						+ "		[*] sclan - scan lan\n"
						+ "		[*] si - scan local interfaces(non active aswell)\n"
						+ "		[*] sia - scan active interfaces\n");
			}
			else if(jftf.getText().toLowerCase().contains("cls"))
			{
				jftf.setText("Console# ");
				this.win.setText("Mainframe: ");
			}
		}
	}
}
