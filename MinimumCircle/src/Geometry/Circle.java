package Geometry;

import java.util.List;

public class Circle {
	public Point center;
	public double radius;
	
	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public static Circle CircleFrom2Points(Point p, Point q) {
		Point center = new Point((p.x + q.x) * 0.5, (p.y + q.y) * 0.5);
		double radius = center.distance(p);
		return new Circle(center,radius);
	}
	
	public static Circle CircleFrom3Points(Point p, Point q, Point r) {
		if(Point.colinearCheck(p, q, r)) {
			return new Circle(new Point(0,0),0);
		}
		else if(p.x == q.x && p.y == q.y) {
			return CircleFrom2Points(p,r);
		}
		else if(p.x == r.x && p.y == r.y) {
			return CircleFrom2Points(q,r);
		}
		else if(q.x == r.x && q.y == r.y) {
			return CircleFrom2Points(p,r);
		}
		
		double x12 = p.x - q.x; 
		double x13 = p.x - r.x; 

		double y12 = p.y - q.y; 
		double y13 = p.y - r.y; 

		double y31 = r.y - p.y; 
		double y21 = q.y - p.y; 

		double x31 = r.x - p.x; 
		double x21 = q.x - p.x; 

		   
		double sx13 = (Math.pow(p.x, 2) - Math.pow(r.x, 2)); 

		double sy13 = (Math.pow(p.y, 2) - Math.pow(r.y, 2)); 

		double sx21 = (Math.pow(q.x, 2) - Math.pow(p.x, 2)); 
						
		double sy21 = (Math.pow(q.y, 2) - Math.pow(p.y, 2)); 

		double f = ((sx13) * (x12) 
				+ (sy13) * (x12) 
				+ (sx21) * (x13) 
				+ (sy21) * (x13)) 
				/ (2 * ((y31) * (x12) - (y21) * (x13))); 
		double g = ((sx13) * (y12) 
				+ (sy13) * (y12) 
				+ (sx21) * (y13) 
				+ (sy21) * (y13)) 
				/ (2 * ((x31) * (y12) - (x21) * (y13))); 

		double c = -Math.pow(p.x, 2) - Math.pow(p.y, 2) - 2 * g * p.x - 2 * f * p.y; 

		// equation of circle be x^2 + y^2 + 2*g*x + 2*f*y + c = 0 
		// where center is (h = -g, k = -f) and radius r 
		// as r^2 = h^2 + k^2 - c 
		double h = -g; 
		double k = -f; 
		double sqr_of_r = h * h + k * k - c; 
 
		double radius = Math.sqrt(sqr_of_r); 
		return new Circle(new Point(h,k),radius);
	}
	
	public boolean isInside(Point p) {
		return Math.pow(this.radius, 2) >= this.center.distanceSquared(p);
	}
	
	public boolean isEnclosingCircle(List<Point> points) {
		for(Point p: points) {
			if(!this.isInside(p)) {
				return false;
			}
		}
		
		return true;
	}
	
}
