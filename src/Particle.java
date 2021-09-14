import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Particle{
	float px;
	float py;
	float lx;
	float ly;
	
	float lastX, lastY;
	
	int STATE;
	int AlarmState = 1;
	
	boolean duringCollision = false;
	int collisionDuration = 0;
	
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	Color color = Color.white;
	
	public Particle(float px, float py, float lx, float ly) {
		this.px = px; this.py = py;
		this.lx = lx; this.ly = ly;
		this.lastX = px;
		this.lastY = py;
	}
	
	public void Execute() {
		if (duringCollision == true) {
			color = Color.red;
			collisionDuration--;
			if (collisionDuration <= 0)
				duringCollision = false;
		} else {
			color = Color.white;
			RandomMove();
			
		}

	}
	
	public void RandomMove() {

			switch(STATE) {
			case 1:
				px++; break;
			case 2:
				px--; break;
			case 3:
				py++; break;
			case 4:
				py--; break;
			case 5:
				px++; py++; break;
			case 6:
				px--; py--; break;
			case 7:
				px++; py--; break;
			case 8:
				px--; py++; break;
		    default:
		    	break;
			}
			
			this.AlarmState++;
			if (AlarmState > 100) {
				Random r = new Random();
				this.STATE = r.nextInt(5);
				AlarmState = 0;
			}
			
			if (this.px < 0 + 8) this.px = 0 + 8;
			if (this.py < 32) this.py = 32;
			if (this.px > this.lx - 20) this.px = this.lx - 20;
			if (this.py > this.ly - 20) this.py = this.ly - 20;
			
			//CollisionCheck();
			
			//particles = new ArrayList<Particle>();
		
		lastX = px;
		lastY = py;
	}
	
	public void CollisionCheck() {
		//Collision detection
		for(int i = 0; i < particles.size(); i++) 
		{
			if(particles.get(i) != this) 
			{
				Particle other = particles.get(i);
				if(px <= other.px + 8 &&
				   px + 8 >= other.px &&
				   py <= other.py + 8 &&
				   py + 8 >= other.py) 
				{
					duringCollision = true;
					collisionDuration = 10;
					Collide();
				} 
			}
		}
	}
	
	public void CollisionCheckQuad(Particle p) {
		//Collision detection
			if(p != this) 
			{
				Particle other = p;
				if(px <= other.px + 8 &&
				   px + 8 >= other.px &&
				   py <= other.py + 8 &&
				   py + 8 >= other.py) 
				{
					duringCollision = true;
					collisionDuration = 10;
					Collide();
			}
		}
	}
	
	public void Collide() 
	{
		px = lastX;
		py = lastY;
		return;
	}
	
}
