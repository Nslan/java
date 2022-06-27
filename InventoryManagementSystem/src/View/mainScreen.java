package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
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
	public mainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(0, 0, 900, 91);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INVENTORY MANAGEMENT SYSTEM MAIN FORM");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(164, 11, 563, 69);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				
				productScreen prdScr = new productScreen();
				
				prdScr.setVisible( true );
				
				
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(mainScreen.class.getResource("/res/move-by-trolley.png")));
		lblNewLabel_1.setBounds(85, 184, 115, 115);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				
				orderScreen ordScr = new orderScreen();
				
				ordScr.setVisible(true);
				
			}
		});
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(mainScreen.class.getResource("/res/order2.png")));
		lblNewLabel_2.setBounds(85, 393, 115, 117);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				
				CategoryScreen cScr = new CategoryScreen();
				
				cScr.setVisible(true);
				
				
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon(mainScreen.class.getResource("/res/category.png")));
		lblNewLabel_3.setBounds(378, 208, 100, 91);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				
				customerScreen custScr = new customerScreen();
				
				custScr.setVisible(true);
				
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon(mainScreen.class.getResource("/res/user4.png")));
		lblNewLabel_4.setBounds(357, 393, 121, 128);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				manageUserScreen mUsrScr = new manageUserScreen();
				
				mUsrScr.setVisible(true);
				
			}
		});
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon(mainScreen.class.getResource("/res/user1.png")));
		lblNewLabel_5.setBounds(671, 184, 121, 115);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(" ");
		lblNewLabel_6.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
				dispose();
				System.exit(0);
				
			}
		});
		lblNewLabel_6.setIcon(new ImageIcon(mainScreen.class.getResource("/res/shutdown (1).png")));
		lblNewLabel_6.setBounds(671, 415, 121, 106);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Product");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setForeground(new Color(220, 20, 60));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(85, 154, 115, 24);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("User");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_7_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setBounds(671, 149, 115, 24);
		contentPane.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Category");
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_7_2.setBounds(378, 154, 115, 24);
		contentPane.add(lblNewLabel_7_2);
		
		JLabel lblNewLabel_7_3 = new JLabel("Customer");
		lblNewLabel_7_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_3.setForeground(new Color(220, 20, 60));
		lblNewLabel_7_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7_3.setBounds(378, 358, 115, 24);
		contentPane.add(lblNewLabel_7_3);
		
		JLabel lblNewLabel_7_4 = new JLabel("Order");
		lblNewLabel_7_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_4.setForeground(new Color(220, 20, 60));
		lblNewLabel_7_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7_4.setBounds(85, 358, 115, 24);
		contentPane.add(lblNewLabel_7_4);
		
		JLabel lblNewLabel_7_5 = new JLabel("LogOut");
		lblNewLabel_7_5.setForeground(new Color(220, 20, 60));
		lblNewLabel_7_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_5.setBounds(671, 358, 115, 24);
		contentPane.add(lblNewLabel_7_5);
		setUndecorated(true);
		this.setLocationRelativeTo(null);//ekranýn ortasýnda acýlmasýný saglar.
	}
}
