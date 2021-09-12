	public class Rect {
		int x,y,w,h;
		public Rect(int x, int y, int w, int h) {this.x = x; this.y = y; this.w = w; this.h = h;}
		
		public Boolean Contains(Particle p) 
		{
			return (p.px + 8 > x &&
					p.px < x + w &&
					p.py + 8 > y &&
					p.py < y + h);
		}
	}
