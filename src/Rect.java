	public class Rect {
		float x,y,w,h;
		public Rect(float x, float y, float w, float h) {this.x = x; this.y = y; this.w = w; this.h = h;}
		
		public Boolean Contains(Particle p) 
		{
			return (p.px + 8 > x &&
					p.px < x + w &&
					p.py + 8 > y &&
					p.py < y + h);
		}
	}
