import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class faturaMakbuzua extends JFrame {

	private JPanel contentPane;

	Double totalAmount = 0.0;
	Double cash = 0.0;
	Double balance = 0.0;
	Double bHeight = 0.0;

	ArrayList<String> itemName = new ArrayList<>();
	ArrayList<String> quantity = new ArrayList<>();
	ArrayList<String> itemPrice = new ArrayList<>();
	ArrayList<String> subtotal = new ArrayList<>();

	private JTextField txtitemname;
	private JTextField txtquantity;
	private JTextField txtitemprice;
	private JTextField txtsubtotal;
	private JTextField txttotalAmount;
	private JTextField txtcash;
	private JTextField txtbalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					faturaMakbuzua frame = new faturaMakbuzua();
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
	public faturaMakbuzua() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel llb_title = new JLabel("Fatura \u00D6deme Sistemi");
		llb_title.setHorizontalAlignment(SwingConstants.CENTER);
		llb_title.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title.setBounds(77, 24, 216, 25);
		contentPane.add(llb_title);

		JLabel llb_title_1 = new JLabel("Item name");
		llb_title_1.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_1.setBounds(10, 115, 192, 25);
		contentPane.add(llb_title_1);

		JLabel llb_title_2 = new JLabel("Quantity");
		llb_title_2.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_2.setBounds(10, 165, 192, 25);
		contentPane.add(llb_title_2);

		JLabel llb_title_3 = new JLabel("Item Price");
		llb_title_3.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_3.setBounds(10, 215, 192, 25);
		contentPane.add(llb_title_3);

		JLabel llb_title_4 = new JLabel("Sub Total");
		llb_title_4.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_4.setBounds(10, 265, 192, 25);
		contentPane.add(llb_title_4);

		JLabel llb_title_5 = new JLabel("Total Amount");
		llb_title_5.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_5.setBounds(10, 410, 192, 25);
		contentPane.add(llb_title_5);

		JLabel llb_title_6 = new JLabel("Cash");
		llb_title_6.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_6.setBounds(10, 472, 192, 25);
		contentPane.add(llb_title_6);

		JLabel llb_title_7 = new JLabel("Balance");
		llb_title_7.setHorizontalAlignment(SwingConstants.LEFT);
		llb_title_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		llb_title_7.setBounds(10, 530, 192, 25);
		contentPane.add(llb_title_7);

		txtitemname = new JTextField();
		txtitemname.setBounds(165, 108, 173, 35);
		contentPane.add(txtitemname);
		txtitemname.setColumns(10);

		txtquantity = new JTextField();
		txtquantity.setColumns(10);
		txtquantity.setBounds(165, 159, 173, 35);
		contentPane.add(txtquantity);

		txtitemprice = new JTextField();
		txtitemprice.setColumns(10);
		txtitemprice.setBounds(165, 210, 173, 35);
		contentPane.add(txtitemprice);

		txtsubtotal = new JTextField();
		txtsubtotal.setColumns(10);
		txtsubtotal.setBounds(165, 261, 173, 35);
		contentPane.add(txtsubtotal);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				itemName.add(txtitemname.getText());
				quantity.add(txtquantity.getText());
				itemPrice.add(txtitemprice.getText());
				subtotal.add(txtsubtotal.getText());
				totalAmount = totalAmount + Double.valueOf(txtsubtotal.getText());
				txttotalAmount.setText(totalAmount + "");

				clear();

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(249, 331, 89, 23);
		contentPane.add(btnAdd);

		txttotalAmount = new JTextField();
		txttotalAmount.setColumns(10);
		txttotalAmount.setBounds(165, 403, 173, 35);
		contentPane.add(txttotalAmount);

		txtcash = new JTextField();
		txtcash.setColumns(10);
		txtcash.setBounds(165, 463, 173, 35);
		contentPane.add(txtcash);

		txtbalance = new JTextField();
		txtbalance.setColumns(10);
		txtbalance.setBounds(165, 521, 173, 35);
		contentPane.add(txtbalance);

		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bHeight = Double.valueOf(itemName.size());
				// JOptionPane.showMessageDialog(rootPane, bHeight);

				PrinterJob pj = PrinterJob.getPrinterJob();
				pj.setPrintable(new BillPrintable(), getPageFormat(pj));
				try {
					pj.print();

				} catch (PrinterException ex) {
					ex.printStackTrace();
				}

			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPrint.setBounds(165, 579, 71, 23);
		contentPane.add(btnPrint);

		JButton btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPay.setBounds(267, 579, 71, 23);
		contentPane.add(btnPay);
	}

	private void clear() {
		txtitemname.setText("");
		txtquantity.setText("");
		txtitemprice.setText("");
		txtsubtotal.setText("");
	}

	public PageFormat getPageFormat(PrinterJob pj) {

		PageFormat pf = pj.defaultPage();
		Paper paper = pf.getPaper();

		double bodyHeight = bHeight;
		double headerHeight = 5.0;
		double footerHeight = 5.0;
		double width = cm_to_pp(8);
		double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
		paper.setSize(width, height);
		paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);

		return pf;
	}

	protected static double cm_to_pp(double cm) {
		return toPPI(cm * 0.393600787);
	}

	protected static double toPPI(double inch) {
		return inch * 72d;
	}

	public class BillPrintable implements Printable {

		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

			int r = itemName.size();
			ImageIcon icon = new ImageIcon("res/LOGO.png");
			int result = NO_SUCH_PAGE;
			if (pageIndex == 0) {

				Graphics2D g2d = (Graphics2D) graphics;
				double width = pageFormat.getImageableWidth();
				g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

				// FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

				try {
					int y = 20;
					int yShift = 10;
					int headerRectHeight = 15;
					// int headerRectHeighta=40;

					g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
					g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
					y += yShift + 30;
					g2d.drawString("-------------------------------------", 12, y);
					y += yShift;
					g2d.drawString("         CodeGuid.com        ", 12, y);
					y += yShift;
					g2d.drawString("   No 00000 Address Line One ", 12, y);
					y += yShift;
					g2d.drawString("   Address Line 02 SRI LANKA ", 12, y);
					y += yShift;
					g2d.drawString("   www.facebook.com/CodeGuid ", 12, y);
					y += yShift;
					g2d.drawString("        +94700000000      ", 12, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 12, y);
					y += headerRectHeight;

					g2d.drawString(" Item Name                  Price   ", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += headerRectHeight;

					for (int s = 0; s < r; s++) {
						g2d.drawString(" " + itemName.get(s) + "                            ", 10, y);
						y += yShift;
						g2d.drawString("      " + quantity.get(s) + " * " + itemPrice.get(s), 10, y);
						g2d.drawString(subtotal.get(s), 160, y);
						y += yShift;

					}

					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Total amount:               " + txttotalAmount.getText() + "   ", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Cash      :                 " + txtcash.getText() + "   ", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Balance   :                 " + txtbalance.getText() + "   ", 10, y);
					y += yShift;

					g2d.drawString("*************************************", 10, y);
					y += yShift;
					g2d.drawString("       THANK YOU COME AGAIN            ", 10, y);
					y += yShift;
					g2d.drawString("*************************************", 10, y);
					y += yShift;
					g2d.drawString("       SOFTWARE BY:NOACODE          ", 10, y);
					y += yShift;
					g2d.drawString("   CONTACT: contact@noacode.com       ", 10, y);
					y += yShift;

				} catch (Exception e) {
					e.printStackTrace();
				}

				result = PAGE_EXISTS;
			}
			return result;
		}
	}

}
