package Geometry;

public class Point {
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point subtract(Point p) {
		return new Point(p.x-this.x, p.y-this.y);
	}
	
	public Point sum(Point p) {
		return new Point(p.x+this.x, p.y+this.y);
	}
	
	public Point multiply(double d) {
		return new Point(this.x*d , this.y*d);
	}
	
	public Point divide(double d) {
		return new Point(this.x/d , this.y/d);
	}
	
	public double distance(Point p) {
		double distanceX = Math.abs(this.x-p.x);
		double distanceY = Math.abs(this.y-p.y);
		
		return Math.sqrt(Math.pow(distanceX, 2)+Math.pow(distanceY, 2));
	}
	
	public double distanceSquared(Point p) {
		double distanceX = Math.abs(this.x-p.x);
		double distanceY = Math.abs(this.y-p.y);
		
		return Math.pow(distanceX, 2)+Math.pow(distanceY, 2);
	}
	
	public static boolean colinearCheck(Point p, Point q, Point r) {
		return Math.abs(p.x * (q.y - r.y) + q.x * (r.y - p.y) + r.x * (p.y - q.y)) == 0.0;
	}
}
