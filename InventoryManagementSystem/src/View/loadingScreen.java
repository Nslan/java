package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

public class loadingScreen extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private static loginScreen lgnscr = new loginScreen();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
					loadingScreen frame = new loadingScreen();
					frame.setVisible(true); 
					
					try {
						
						for( int i=0; i<=1000; i++ ) {
							
							
							loadingScreen.progressBar.setValue(i);
							lblNewLabel_1.setText("%" + i);
							
							Thread.sleep(50);
							
							if( i == 100 ) {
								
								frame.setVisible(false);
								lgnscr.setVisible(true);
								
							}
							
							
						}
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
	}

	/**
	 * Create the frame.
	 */
	public loadingScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory Management System");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(129, 11, 377, 24);
		contentPane.add(lblNewLabel);
		
		 lblNewLabel_1 = new JLabel("");
		 lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(291, 251, 98, 24);
		contentPane.add(lblNewLabel_1);
		
		 progressBar = new JProgressBar();
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setForeground(new Color(220, 20, 60));
		progressBar.setBounds(0, 286, 650, 14);
		contentPane.add(progressBar);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(loadingScreen.class.getResource("/res/dB6.png")));
		lblNewLabel_2.setBounds(254, 99, 128, 120);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("InvSys");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setForeground(new Color(220, 20, 60));
		lblNewLabel_3.setBounds(368, 99, 82, 24);
		contentPane.add(lblNewLabel_3);
		setUndecorated(true);
		this.setLocationRelativeTo(null);//ekranýn ortasýnda acýlmasýný saglar.
	}
}
