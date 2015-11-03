import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClienteGustavo{
	
	JTextField outgoing;
	PrintWriter writer;
	Socket chatSocket;
	
	public void go()
	{
		JFrame frame = new JFrame("Cliente");
		JPanel panel = new JPanel();
		outgoing = new JTextField(20);
		JButton send = new JButton("Send");
		send.addActionListener(new SendButtonListener());
		panel.add(outgoing);
		panel.add(send);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		setUpNetworking();
		frame.setSize(400,500);
		frame.setVisible(true);
	}
	
	private void setUpNetworking()
	{
		try
		{
			chatSocket = new Socket("127.0.0.1",3000);
			writer = new PrintWriter(chatSocket.getOutputStream());
			System.out.println("networking estabilished");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public class SendButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			try
			{
				writer.println(outgoing.getText());
				writer.flush();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			outgoing.setText("Frase| ");
			outgoing.requestFocus();
		}
	}
	
	public static void main(String[] args)
	{
		new ClienteGustavo().go();
	}
}














