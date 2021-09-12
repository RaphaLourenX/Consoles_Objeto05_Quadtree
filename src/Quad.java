import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Quad {
	
	Rect boundary;
	
	int limit;
	
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	Quad[] subQuads = new Quad[4];
	
	Rect[] subRects = new Rect[4];
	
	boolean divided = false;
	
	public Quad(Rect boundary, int limit)
	{
		this.boundary = boundary;
		this.limit = limit;
	}
	
	public void Insert(Particle p) 
	{
		
		if(!boundary.Contains(p)) return;
		
		if(particles.size() < limit) particles.add(p);
		else
		{
			if(!divided) Subdivide();
			subQuads[0].Insert(p);
			subQuads[1].Insert(p);
			subQuads[2].Insert(p);
			subQuads[3].Insert(p);
		}
	}
	
	private void Subdivide() 
	{
		subRects[0] = new Rect(boundary.x, boundary.y, boundary.w / 2, boundary.h / 2);
		subRects[1] = new Rect(boundary.w / 2, 0, boundary.w, boundary.h / 2);
		subRects[2] = new Rect(boundary.w / 2, boundary.h / 2, boundary.w, boundary.h);
		subRects[3] = new Rect(boundary.x, boundary.h / 2, boundary.w / 2, boundary.h);
		
		subQuads[0] = new Quad(subRects[0], limit);
		subQuads[1] = new Quad(subRects[1], limit);
		subQuads[2] = new Quad(subRects[2], limit);
		subQuads[3] = new Quad(subRects[3], limit);
		
		divided = true;
	}
	
	public void Draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawRect(boundary.x, boundary.y, boundary.w, boundary.h);
		for(Quad q : subQuads) if(q != null) q.Draw(g);
	}
	
}