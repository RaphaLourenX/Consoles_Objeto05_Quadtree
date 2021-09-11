import java.util.Random;

public class Particle extends Thread {
	int px;
	int py;
	int lx;
	int ly;
	
	int STATE;
	int AlarmState = 1;
	
	public Particle(int px, int py, int lx, int ly) {
		this.px = px; this.py = py;
		this.lx = lx; this.ly = ly;
	}
	
	public void RandomMove() {
		try {
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
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			RandomMove();
		}
	}
}
