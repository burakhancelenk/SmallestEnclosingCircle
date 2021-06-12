package Algorithms;
import Geometry.Circle;
import Geometry.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinCircleAlgorithms {
	private static XORShiftRandom rnd = new XORShiftRandom();

	public static Circle NaiveAlgorithm(List<Point> points) {
		if(points.size() == 1) {
			return new Circle(new Point(points.get(0).x,points.get(0).y),0);
		}
		else if(points.isEmpty()) {
			return new Circle(new Point(0,0),0);
		}
		
		Circle minCircle = null;
		
		// 2 points case
		for(Point p: points) {
			for(Point q: points) {
				if(p != q) {
					minCircle = Circle.CircleFrom2Points(p, q);
					if(minCircle.isEnclosingCircle(points)) {
						return minCircle;
					}
				}
			}
		}
		
		minCircle = new Circle(new Point(0,0),Double.POSITIVE_INFINITY);
		
		// 3 points case
		for(Point p: points) {
			for(Point q: points) {
				for(Point r: points) {
					if(p != q && p!=r && q!=r) {
						Circle tempCircle = Circle.CircleFrom3Points(p, q, r);
						if(tempCircle.radius < minCircle.radius && tempCircle.isEnclosingCircle(points)) {
							minCircle = tempCircle;
						}
					}
				}
			}
		}
		
		return minCircle;
	}
	
	public static Circle WelzlAlgorithm(List<Point> points) {
		ArrayList<Point> points_copy = new ArrayList<Point>();
		ArrayList<Point> boundary = new ArrayList<Point>();
		
		points_copy.addAll(points);
		return MinDisk(points_copy,boundary);
	}
	
	private static Circle MinDisk(List<Point> P, List<Point> R) {
		Circle minCircle = null;
		if(R.size() == 3) {
			minCircle = Circle.CircleFrom3Points(R.get(0), R.get(1), R.get(2));
		}
		else if(P.size() == 1 && R.size() == 1) {
			minCircle = Circle.CircleFrom2Points(P.get(0), R.get(0));
		}
		else if(R.size() == 2 && P.isEmpty()) {
			minCircle = Circle.CircleFrom2Points(R.get(0), R.get(1));
		}
		else if(P.size() == 1 && R.isEmpty()) {
			minCircle = new Circle(P.get(0),0);
		}
		else {
			Point p = P.remove(rnd.nextInt(P.size()));
			minCircle = MinDisk(P,R);
			
			if(minCircle != null && !minCircle.isInside(p)) {
				R.add(p);
				minCircle = MinDisk(P,R);
				R.remove(p);
				P.add(p);
			}
		}
		
		return minCircle;
		
	}
	
	public static Circle RitterAlgorithm(List<Point> P) {
		ArrayList<Point> points_copy = new ArrayList<Point>();
		points_copy.addAll(P);
		Collections.shuffle(points_copy);
		
		Point x = points_copy.remove(rnd.nextInt(points_copy.size()));
		Point y = points_copy.get(0);
		
		for(Point p : points_copy) {
			if(x.distanceSquared(p) > x.distanceSquared(y)) {
				y = p;
			}
		}
		
		points_copy.remove(y);
		Point z = points_copy.get(0);
		
		for(Point p : points_copy) {
			if(y.distanceSquared(p) > y.distanceSquared(z)) {
				z = p;
			}
		}
		
		points_copy.remove(z);
		Circle minCircle = Circle.CircleFrom2Points(y, z);
		
		for(Point p : points_copy) {
			if(!minCircle.isInside(p)) {
				Point cp = p.subtract(minCircle.center);
				Point unitVector = cp.divide(p.distance(minCircle.center));
				Point p2 = unitVector.multiply(minCircle.radius).sum(minCircle.center);
				minCircle = Circle.CircleFrom2Points(p, p2);
			}
		}
		
		return minCircle;
	}
	
}
