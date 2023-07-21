//Saturn V Extension and Enhanced Animation Extension.java
//Deep M.           ITCS

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Falcon9LiftOffExtensions extends JPanel {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private BufferedImage image;
	private Graphics g;
	private SaturnV saturn;
	private Snowflake[] stars;
	private Timer timer;
	private int colChange;

	public Falcon9LiftOffExtensions() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

		colChange = 255;
		g.setColor(new Color(0, 0, colChange));

		g.fillRect(0, 0, WIDTH, HEIGHT);
		saturn = new SaturnV(WIDTH / 2, HEIGHT, 60, 150, new Color(255, 128, 0), "Saturn V");
		saturn.setDeltaTime(.01);
		saturn.draw(g);
//		stars
		stars = new Snowflake[20];

		System.out.println(saturn.getFlightTime() + ", " + saturn.getMass() + ", " + saturn.getFNet() + ", "
				+ saturn.getAcceleration() + ", " + saturn.getVelocity() + ", " + saturn.getAltitude());

		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.setColor(Color.BLUE);
		g.drawString("Time: " + (int) saturn.getFlightTime() + " s", 25, 20);
		g.drawString("Velocity: " + (int) saturn.getVelocity() + " m/s", 25, 40);
		g.drawString("Mass: " + (int) saturn.getMass() + " kg", 25, 60);
		g.drawString("Altitude: " + (int) saturn.getAltitude() + " m", 25, 80);

		timer = new Timer(10, new TimerListener());
		timer.start();
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			g.setColor(new Color(0, 0, colChange));
			g.fillRect(0, 0, WIDTH, HEIGHT);

			saturn.move(HEIGHT);
			saturn.draw(g);

			g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			if (saturn.getFlightTime() >= 213) {
				g.drawString("Stage 1 Complete", 25, 20);
			} 
			else {
				System.out.println(saturn.getFlightTime() + ", " + saturn.getMass() + ", " + saturn.getFNet() + ", "
						+ saturn.getAcceleration() + ", " + saturn.getVelocity() + ", " + saturn.getAltitude());
				g.drawString("Time: " + (int) saturn.getFlightTime() + " s", 25, 20);
				g.drawString("Velocity: " + (int) saturn.getVelocity() + " m/s", 25, 40);
				g.drawString("Mass: " + (int) saturn.getMass() + " kg", 25, 60);
				g.drawString("Altitude: " + (int) saturn.getAltitude() + " m", 25, 80);
			}
			
			if ((saturn.getFlightTime() % 1 >= 0 && saturn.getFlightTime() % 1 <= .01) && colChange > 0) {
				colChange--;
			} 
			
			if (colChange <= 1) {
				colChange = 0;
			}

			if (colChange <= 100 && (saturn.getFlightTime() <= 213)) {
				g.setColor(Color.WHITE);
				for (int i = 0; i < stars.length; i++) {
					int randX = (int) (Math.random() * WIDTH);
					int randY = (int) (Math.random() * HEIGHT);
					stars[i] = new Snowflake(randX, randY);
					stars[i].draw(g);
				}
			}
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Saturn V Lift Off!");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Falcon9LiftOffExtensions());
		frame.setVisible(true);
	}

}
