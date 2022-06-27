package bank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainMenu extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenu frame = new mainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1900, 1065);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(47, 79, 79));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1874 , 22);
		desktopPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File"); 
		menuBar.add(mnFile);
		
		JMenuItem mnCustomer = new JMenuItem("Customer");
		mnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				Customer cust = new Customer( );

				desktopPane.add( cust );
				
				cust.setVisible( true );
				
			}
		});
		mnCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		mnFile.add(mnCustomer);
		
		JMenuItem mnAccount = new JMenuItem("Account");
		mnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				account  act = new account();
				
				desktopPane.add( act );
				
				act.setVisible( true );
				
			}
		});
		mnAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mnFile.add( mnAccount );
		
		JMenu mnEdit = new JMenu("Transaction");
		menuBar.add(mnEdit);
		
		JMenuItem mnDeposit = new JMenuItem("Deposit");
		mnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Deposito dep = new Deposito();
				
				desktopPane.add( dep );
				
				dep.setVisible( true );
				
			}
		});
		mnEdit.add( mnDeposit );
		
		JMenuItem mnýtmNewMenuItem_2 = new JMenuItem("Withdraw");
		mnýtmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				withdraw wit = new withdraw();
				
				desktopPane.add( wit );
				
				wit.setVisible( true );
				
			}
		});
		mnEdit.add(mnýtmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("Transfer");
		menuBar.add(mnNewMenu);
		
		JMenuItem mnýtmNewMenuItem_1 = new JMenuItem("Account to Account");
		mnýtmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Transfer tra = new Transfer();
				
				desktopPane.add( tra );
				
				tra.setVisible( true );
						
			}
		});
		mnNewMenu.add(mnýtmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Report");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mnýtmNewMenuItem_3 = new JMenuItem("Customer Report");
		mnNewMenu_1.add(mnýtmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Balance");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mnýtmNewMenuItem_4 = new JMenuItem("Balance Check");
		mnNewMenu_2.add(mnýtmNewMenuItem_4);
		
		JMenu mnNewMenu_3 = new JMenu("Account");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mnýtmNewMenuItem_5 = new JMenuItem("User Account");
		mnNewMenu_3.add(mnýtmNewMenuItem_5);
		
	}
}
