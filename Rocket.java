import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Rocket {

	private double midX;
	private double midY;
	private double width;
	private double height;
	private Color color;
	private String name;

	private double ySpeed;
	private double firstX = midX;
	
	public Rocket(int middleX, int middleY, int aWidth, int aHeight, Color aColor, String words) {
		midX = middleX;
		midY = middleY;
		width = aWidth;
		height = aHeight;
		color = aColor;
		name = words;
		firstX = midX;
	}

	public void drawFlame(Graphics g) {
		int midX = (int)this.midX;
		int midY = (int)this.midY;
		int width = (int)this.width;
		int firstX = (int)this.firstX;
		int height = (int)this.height;
		if (midY > 0) {
		int[] flameX = {midX-width/4, firstX, midX+width/4};
		int[] flameY = {midY, midY+height/4, midY};
		g.setColor(Color.black);
		g.drawPolygon(flameX, flameY, 3);

		int randCol = (int)(Math.random() *3);
		if (randCol == 0) {
			g.setColor(Color.pink);
		}
		else if(randCol == 1) {
			g.setColor(Color.ORANGE);
		}
		else {
			g.setColor(Color.yellow);
		}
		g.fillPolygon(flameX, flameY, 3);
		}
	}
	public void draw(Graphics g) {
		int midX = (int)this.midX;
		int midY = (int)this.midY;
		int width = (int)this.width;
		int height = (int)this.height;
//		sides
		int[] xPs = { midX - (width / 2) - (width / 10), midX, midX + (width / 2) + (width / 10) };
		int[] yPs = { midY - height, midY - height - (height / 3), midY - height };

		int[] xPs1 = { midX + width / 2, midX + width / 2, (midX + width / 2) + (width / 4) * 3 };
		int[] yPs1 = { midY, midY - height / 4, midY };

		int[] xPs2 = { midX - width / 2, midX - width / 2, (midX - width / 2) - (width / 4) * 3 };
		int[] yPs2 = { midY, midY - height / 4, midY };

		int[] xPs3 = { midX - width / 2, midX - width / 2, (midX - width / 2) - width / 2 };
		int[] yPs3 = { midY - height / 4, midY - height / 2, midY - height / 5 };

		int[] xPs4 = { midX + width / 2, midX + width / 2, (midX + width / 2) + width / 2 };
		int[] yPs4 = { midY - height / 4, midY - height / 2, midY - height / 5 };

//		coloring
		g.setColor(color);
		g.fillRect(midX - (width / 2), midY - height, width, height);
		g.fillOval(midX - (width / 4), midY - height + (height / 12), width / 2, width / 2);
		g.fillPolygon(xPs, yPs, 3);
		g.fillPolygon(xPs1, yPs1, 3);
		g.fillPolygon(xPs2, yPs2, 3);
		g.fillPolygon(xPs3, yPs3, 3);
		g.fillPolygon(xPs4, yPs4, 3);

//		borders
		g.setColor(Color.BLACK);
//		body
		g.drawRect(midX - (width / 2), midY - height, width, height);
//		window
		g.drawOval(midX - (width / 4), midY - height + (height / 12), width / 2, width / 2);
//		top
		g.drawPolygon(xPs, yPs, 3);
//		sides
		g.drawPolygon(xPs1, yPs1, 3);
		g.drawPolygon(xPs2, yPs2, 3);
		g.drawPolygon(xPs3, yPs3, 3);
		g.drawPolygon(xPs4, yPs4, 3);

//		vertical text
		String str = name;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.PLAIN, width / 6));
		int changingY = midY - (height / 24) * 11;
		for (int i = 0; i < str.length(); i++) {
			g.drawString("" + str.charAt(i), midX - width / 18, changingY);
			changingY += width / 6;
		}
		
//		draw the flame
		drawFlame(g);
	}

	public int getX() {
		return (int)midX;
	}

	public double getY() {
		return midY;
	}

	public double getYSpeed() {
		return ySpeed;
	}

	public void setX(double x) {
		midX = x;
	}

	public void setY(double y) {
		midY = y;
	}

	public void setYSpeed(double ySp) {
		ySpeed = ySp;
	}
	public double getHeight() {
		return height;
	}

	public void move(int edge) {
		setYSpeed((int)ySpeed);

		if ((midY - height - (height / 3)) > edge) {
			midY -= ySpeed;
			int flameRandom = (int)(Math.random() * (width/2) + midX-width/4);
			firstX = flameRandom;
		}


	}
}
