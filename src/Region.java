import java.util.ArrayList;

public class Region {
	int iX;
	int iY;
	int fX;
	int fY;
	ArrayList<Region> regions;
	Particle[] particles;
	
	public Region(int iX, int iY, int fX, int fY, Particle[] particles, ArrayList<Region> regions) {
        
		this.iX = iX; this.iY = iY;
		this.fX = fX; this.fY = fY;
		this.particles = particles;
		this.regions = regions;
	}
	

	public void createQuadrant(int index) {
		int sizeX = (fX - iX)/2;
		int sizeY = (fY - iY)/2;
		
		switch(index) {
		case 0:
			regions.add(new Region(iX, iY, sizeX, sizeY, particles, regions));
			break;
		case 1:
			regions.add(new Region(iX + sizeX, iY, sizeX, sizeY, particles, regions));
			break;
		case 2:
			regions.add(new Region(iX, iY + sizeY, sizeX, sizeY, particles, regions));
			break;
		case 3:
			regions.add(new Region(iX + sizeX, iY + sizeY, sizeX, sizeY, particles, regions));
			break;
		default:
			break;
			} 

		
	}
}
