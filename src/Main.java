import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main extends JFrame{
	public static int SCREENRES_X = 640;
	public static int SCREENRES_Y = 360;
	
	public static int PARTICLENUMBER = 0;
	public static int PSIZE = 10;
	
	static Particle[] particles;
	
	public static enum CollisionMode
	{
		NORMAL,
		QUADTREE
	};
	
	public static CollisionMode mode = CollisionMode.NORMAL;
	
	//Initializing Quad
	public static Quad quad;
	
	//Main is the Main Screen
	public Main(int _x, int _y) {
		setSize(_x, _y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void paint(Graphics g) {
		//Set Background Color
		g.setColor(Color.black);
		g.fillRect(0, 0, SCREENRES_X, SCREENRES_Y);
		
		//Creation of Particles
		for (int i = 0; i < particles.length; i++) {
			g.setColor(particles[i].color);
			g.fillOval(particles[i].px, particles[i].py, PSIZE, PSIZE);
			}
		
		//Creation of Quadtree
		if(quad != null) quad.Draw(g);
	
		repaint();
		
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
			particles[i] = new Particle(rx, ry, SCREENRES_X, SCREENRES_Y);
		}
	
		new Main(SCREENRES_X, SCREENRES_Y);
		
		while(true) {
			quad = new Quad(new Rect(0, 0, SCREENRES_X, SCREENRES_Y), 4);
			for(Particle p : particles) quad.Insert(p);
			for(Particle p : particles) p.Execute();
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
