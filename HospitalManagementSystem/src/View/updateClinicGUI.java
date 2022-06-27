package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class updateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinic;
	private static Clinic klinik_nesnem = new Clinic();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateClinicGUI frame = new updateClinicGUI( klinik_nesnem );
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
	public updateClinicGUI(Clinic klinik_nesnem) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 26, 214, 20);
		contentPane.add(lblNewLabel);
		
		fld_clinic = new JTextField();
		fld_clinic.setColumns(10);
		fld_clinic.setBounds(10, 45, 214, 30);
		fld_clinic.setText( klinik_nesnem.getName() );
		contentPane.add(fld_clinic);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( Helper.confirm("sure") ) {
					try {
						
						boolean control = klinik_nesnem.updateClinic( klinik_nesnem.getId() , fld_clinic.getText()  );
						
						if( control = true) {
							Helper.showMsg("success");
							dispose();
						}
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
			}
		});
		btn_updateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btn_updateClinic.setBounds(10, 78, 214, 32);
		contentPane.add(btn_updateClinic);
	}
}
