import java.awt.Color;
import java.util.Random;

public class Particle{
	int px;
	int py;
	int lx;
	int ly;
	
	int lastX, lastY;
	
	int STATE;
	int AlarmState = 1;
	
	boolean duringCollision = false;
	int collisionDuration = 0;
	
	Particle[] particles;
	
	Color color = Color.white;
	
	public Particle(int px, int py, int lx, int ly, Particle[] particles) {
		this.px = px; this.py = py;
		this.lx = lx; this.ly = ly;
		this.lastX = px;
		this.lastY = py;
		this.particles = particles;
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
				this.STATE = r.nextInt(9);
				AlarmState = 0;
			}
			
			if (this.px < 0 + 8) this.px = 0 + 8;
			if (this.py < 32) this.py = 32;
			if (this.px > this.lx - 20) this.px = this.lx - 20;
			if (this.py > this.ly - 20) this.py = this.ly - 20;

		
		//Collision detection
		for(int i = 0; i < particles.length; i++) 
		{
			if(particles[i] != this) 
			{
				Particle other = particles[i];
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
		
		lastX = px;
		lastY = py;
	}
	
	public void Collide() 
	{
		px = lastX;
		py = lastY;
		return;
	}
	
}
