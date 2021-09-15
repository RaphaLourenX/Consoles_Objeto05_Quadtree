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
	public static int SCREENRES_X = 1280;
	public static int SCREENRES_Y = 720;
	
	public static int PARTICLENUMBER = 0;
	public static int PSIZE = 10;
	
	public static long MSTIME = 0;
	
	static ArrayList<Particle> particles = new ArrayList<Particle>();
	
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
		for (int i = 0; i < particles.size(); i++) {
			g.setColor(particles.get(i).color);
			g.fillOval((int)particles.get(i).px, (int)particles.get(i).py, PSIZE, PSIZE);
			}
		
		//Creation of Quadtree
		if(quad != null) quad.Draw(g);
		
		//Draw Speedup Timer
		g.setColor(Color.black);
		g.fillRect(SCREENRES_X - 120, 30, 100, 30);
		g.setColor(Color.white);
		g.drawString(MSTIME + "ms", SCREENRES_X - 100, 60);
		
		java.awt.Toolkit.getDefaultToolkit().sync();
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
		
		int choice = 0;
		
		System.out.println("What kind of collision system do you want to use?\n");
		
		System.out.println("\nPress 1 to normal collision system.\n");
		
		System.out.println("\nPress 2 to Quadtree based collision system.\n");
		
		try {
			choice = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(choice == 1) mode = CollisionMode.NORMAL;
		if(choice == 2) mode = CollisionMode.QUADTREE;
		
		for (int i = 0; i < PARTICLENUMBER; i++) {
			Random r = new Random();
			float rx = r.nextFloat() * SCREENRES_X;
			float ry = r.nextFloat() * SCREENRES_Y;
			particles.add(new Particle(rx, ry, SCREENRES_X, SCREENRES_Y));
		}
		
		if(mode == CollisionMode.NORMAL) for(Particle p : particles) p.particles = particles;
	
		new Main(SCREENRES_X, SCREENRES_Y);
		
		while(true) {
			long start = System.currentTimeMillis();
			
			switch(mode) 
			{
			case NORMAL:
				for(Particle p : particles) p.Execute();
				for(Particle p : particles) p.CollisionCheck();
				break;
			case QUADTREE:
				quad = new Quad(new Rect(0, 0, SCREENRES_X, SCREENRES_Y), 4);
				for(Particle p : particles) quad.Insert(p);
				for(Particle p : particles) p.Execute();
				for(Particle p : particles) p.CollisionCheck();
				for(Particle p : particles) p.particles = new ArrayList<Particle>();
				break;
			}

			long totalTime = System.currentTimeMillis() - start;
			MSTIME = totalTime;
			System.out.println(totalTime + "ms");
			
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	
}
