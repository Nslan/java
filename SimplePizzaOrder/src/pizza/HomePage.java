package pizza;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Connecting;
import Model.kayýtSaleDB;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer; 
 


public class HomePage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField1;
	private JTextField textField2;

	private DefaultTableModel model = new DefaultTableModel();
	private String item;
	private int price;
	private JTextField textField;

	// DB e eeriþerek verileri ekleyebilmek için o sýnýftan bir nesne tanýmlamak
	// gerekli
	private static kayýtSaleDB sale_kayýt_nesnesi = new kayýtSaleDB();

	
	
	//sales_product DB deki deðiþkenler
	private int sale_id;       // bunu sale tablosundaki kayýtlarýn id lerini almak için kullanacagýz
	private String product_name;
	private int PRICE ;
	private int quantity;
	private int total;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(sale_kayýt_nesnesi);// bu homepage in bu tipten bir parametre
																		// alacagýný soyler
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
	public HomePage(kayýtSaleDB sale_kayýt_nesnesi) throws net.sf.jasperreports.engine.fill.JRExpressionEvalException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setForeground(new Color(0, 0, 139));
		panel.setBounds(0, 0, 950, 76);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Pizza Ordering System");
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(319, 23, 318, 31);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(926, 11, 14, 25);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 102, 215, 296);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JRadioButton rBtn1 = new JRadioButton("Small");
		rBtn1.setFont(new Font("Tahoma", Font.BOLD, 12));
		rBtn1.setBounds(60, 35, 109, 23);
		panel_1.add(rBtn1);

		JRadioButton rBtn2 = new JRadioButton("Medium");
		rBtn2.setFont(new Font("Tahoma", Font.BOLD, 12));
		rBtn2.setBounds(60, 95, 109, 23);
		panel_1.add(rBtn2);

		JRadioButton rBtn3 = new JRadioButton("Large");
		rBtn3.setFont(new Font("Tahoma", Font.BOLD, 12));
		rBtn3.setBounds(60, 155, 109, 23);
		panel_1.add(rBtn3);

		JRadioButton rBtn4 = new JRadioButton("Extra Large");
		rBtn4.setFont(new Font("Tahoma", Font.BOLD, 12));
		rBtn4.setBounds(60, 215, 109, 23);
		panel_1.add(rBtn4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(260, 102, 415, 296);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Item", "Price", "Quantity", "Total" }));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Total");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(757, 102, 130, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Payment");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(757, 171, 130, 20);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Balance");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(757, 244, 130, 20);
		contentPane.add(lblNewLabel_1_2);

		textField1 = new JTextField();
		textField1.setBounds(757, 196, 130, 29);
		contentPane.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(757, 269, 130, 29);
		contentPane.add(textField2);

		JButton printBtn = new JButton("Print Invoice");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int total = Integer.parseInt( textField.getText() );
				int payment = Integer.parseInt( textField1.getText() );
				
				int balance = payment - total;
				
				textField2.setText( String.valueOf( balance ) );
				
				try {
					
					//burada sale tablosuna kayýt ettik 
																		// subtotal , payment , balance
					sale_id = sale_kayýt_nesnesi.addSatýsKayýt(textField.getText(), textField1.getText(), textField2.getText());

					int row = table.getRowCount(); // table in kac satýrý var
					
					// burada ise sale product tablosuna kayýt edecegiz
					
					for( int i=0; i< row ; i++ ) {
						
						product_name = (String) table.getValueAt( i, 0 );
						PRICE = (int) table.getValueAt(i, 1);
						quantity = (int) table.getValueAt(i, 2);
						total = (int) table.getValueAt(i, 3);		
								
						sale_kayýt_nesnesi.addSaleProductKayýt( sale_id , product_name, PRICE , quantity , total );
									
					}
					
					JOptionPane.showMessageDialog(null, "Sale Completeeeddd", "Dikkat", JOptionPane.ERROR_MESSAGE );
				
					HashMap mapp = new HashMap();
					mapp.put("getParam", sale_id );
					
					Connecting bag = new Connecting();
 
					try {
							
					
						JasperDesign jdesign  = JRXmlLoader.load("C:\\Users\\nuhas\\eclipse-workspace\\SimplePizzaOrder\\src\\pizza\\Blank_A4.jrxml");  
						JasperReport jreport  = JasperCompileManager.compileReport(jdesign);
						
						
						
						JasperPrint jprint = JasperFillManager.fillReport(jreport, mapp , bag.baglanDB() );
						
						JasperViewer.viewReport( jprint );
						
						
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		printBtn.setBounds(177, 452, 117, 23);
		contentPane.add(printBtn);

		JLabel lblNewLabel_1_3 = new JLabel("Quantity");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(304, 452, 130, 20);
		contentPane.add(lblNewLabel_1_3);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(420, 453, 130, 29);
		contentPane.add(spinner);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(757, 133, 130, 29);
		contentPane.add(textField);

		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rBtn1.isSelected() == true) {
					item = "Small";
					price = 20;
				} else if (rBtn2.isSelected() == true) {
					item = "Medium";
					price = 30;
				} else if (rBtn3.isSelected() == true) {
					item = "Large";
					price = 40;
				} else if (rBtn4.isSelected() == true) {
					item = "Extra Large";
					price = 50;
				}

				int quantity = Integer.parseInt(spinner.getValue().toString());
				int total = quantity * price;

				model = (DefaultTableModel) table.getModel();

				model.addRow(new Object[] {

						item, price, quantity, total, });

				int sum = 0;

				for (int i = 0; i < table.getRowCount(); i++) {

					sum = sum + Integer.parseInt(table.getValueAt(i, 3).toString());

				}

				textField.setText(Integer.toString(sum));

			}
		});
		addBtn.setBounds(54, 452, 89, 23);
		contentPane.add(addBtn);

		JButton removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.removeRow(table.getSelectedRow());

				int sum = 0;

				for (int i = 0; i < table.getRowCount(); i++) {

					sum = sum + Integer.parseInt(table.getValueAt(i, 3).toString());

				}

				textField.setText(Integer.toString(sum));

			}
		});
		removeBtn.setBounds(641, 452, 89, 23);
		contentPane.add(removeBtn);

		setUndecorated(true);
	}
}
