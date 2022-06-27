package Contoller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class myButton extends JButton {

	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 0;

	public myButton() {

		// init Color // baslangic rengi
		color = Color.WHITE; // veya setColor(Color.WHITE);
		// bunlar yesilin farklý tonlarýdýr
		colorOver = new Color(215 , 15 , 55);
		colorClick = new Color(220, 20, 60);
		borderColor = new Color(220, 120, 160);

		setContentAreaFilled(false);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(color);
				
				over = true;
			}
  
 
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(colorClick);
				
				over = false;
			}
			
			
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(colorClick);
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if( over) {
					setBackground(colorOver );
					
				}else {
					setBackground(colorOver);
				}
				
			}
		});

	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}

	public Color getColorOver() {
		return colorOver;
	}

	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}

	public Color getColorClick() {
		return colorClick;
	}

	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// paint Border
		g2.setColor(borderColor);
		// burada geniþlikler biz pencerede butonun geniþliðini ayarlamaka istediðimiz
		// kadar yani oradan geliyoe
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		g2.setColor(getBackground());

		// Border set 2 Pix
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

		super.paintComponent(g);
	}

}
