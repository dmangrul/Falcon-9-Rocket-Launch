//Liftoff.java
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

public class Falcon9LiftOff extends JPanel {

	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private BufferedImage image;
	private Graphics g;
	private Falcon9 falcon;
	private Timer timer;

	public Falcon9LiftOff() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		falcon = new Falcon9(WIDTH / 2, HEIGHT, 60, 150, Color.RED, "Falcon 9");
		falcon.setDeltaTime(.01);
		falcon.draw(g);
		System.out.println(falcon.getFlightTime() + ", " + falcon.getMass() + ", " + falcon.getFNet() + ", "
				+ falcon.getAcceleration() + ", " + falcon.getVelocity() + ", " + falcon.getAltitude());

		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		g.setColor(Color.BLUE);
		g.drawString("Time: " + (int) falcon.getFlightTime() + " s", 25, 20);
		g.drawString("Velocity: " + (int) falcon.getVelocity() + " m/s", 25, 40);
		g.drawString("Mass: " + (int) falcon.getMass() + " kg", 25, 60);
		g.drawString("Altitude: " + (int) falcon.getAltitude() + " m", 25, 80);

		timer = new Timer(10, new TimerListener());
		timer.start();
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			falcon.move(HEIGHT);
			falcon.draw(g);

			g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g.setColor(Color.BLUE);
			if (falcon.getFlightTime() >= 162) {
				g.drawString("Stage 1 Complete", 25, 20);
			} 
			else {
				System.out.println(falcon.getFlightTime() + ", " + falcon.getMass() + ", " + falcon.getFNet() + ", " + falcon.getAcceleration() + ", " + falcon.getVelocity() + ", " + falcon.getAltitude());
				g.drawString("Time: " + (int) falcon.getFlightTime() + " s", 25, 20);
				g.drawString("Velocity: " + (int) falcon.getVelocity() + " m/s", 25, 40);
				g.drawString("Mass: " + (int) falcon.getMass() + " kg", 25, 60);
				g.drawString("Altitude: " + (int) falcon.getAltitude() + " m", 25, 80);
			}
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Falcon 9 Lift Off!");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Falcon9LiftOff());
		frame.setVisible(true);
	}

}
