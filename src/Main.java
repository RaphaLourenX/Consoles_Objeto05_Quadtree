import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends JFrame{
	public static int SCREENRES_X = 1280;
	public static int SCREENRES_Y = 720;
	
	public static int PARTICLENUMBER = 0;
	public static int PSIZE = 10;
	
	static Particle[] particles;
	
	public static enum CollisionMode
	{
		NORMAL,
		QUADTREE
	};
	
	public static CollisionMode mode = CollisionMode.NORMAL;
	
	public Main(int _x, int _y) {
		
		new Time().start();
		setSize(_x, _y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void paint(Graphics g) {
		//Set Background Color
		g.setColor(Color.black);
		g.fillRect(0, 0, SCREENRES_X, SCREENRES_Y);
		
		for (int i = 0; i < particles.length; i++) {
			g.setColor(particles[i].color);
			g.fillOval(particles[i].px, particles[i].py, PSIZE, PSIZE);
			}
		}
		
	public static void main(String args[]) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("How many particles do you want to simulate?\n");
		
		try {
			PARTICLENUMBER = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		particles = new Particle[PARTICLENUMBER];
		
		for (int i = 0; i < PARTICLENUMBER; i++) {
			Random r = new Random();
			int rx = r.nextInt(SCREENRES_X);
			int ry = r.nextInt(SCREENRES_Y);
			particles[i] = new Particle(rx, ry, SCREENRES_X, SCREENRES_Y, particles);
			particles[i].start();
		}
	
		new Main(SCREENRES_X, SCREENRES_Y);
		
	}
	
	public class Time extends Thread{
		public void run() {
			while(true) {
				//CollisionDetection();
				repaint();
			}
		}
	}
	
}
