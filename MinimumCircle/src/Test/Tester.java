package Test;
import java.util.*;

import Algorithms.MinCircleAlgorithms;
import File.FileHandler;
import Geometry.*;

public class Tester {
	public static int NumberOfFiles;
	public static String SamplesPath;
	public static String FileName;
	public static String Extension;
	public static String ResultsPath;
	public static String ResultsName;
	
	public static enum Algorithm{
		WELZL,NAIVE,RITTER
	}
	public static Algorithm currentAlgorithm = Algorithm.NAIVE;
	
	
	public static void TestAlgorithm() {
		ArrayList<Long> timeResults = new ArrayList<Long>();
		ArrayList<Double> radiusResults = new ArrayList<Double>();
		ArrayList<Integer> numberOfPoints = new ArrayList<Integer>();
		ArrayList<Double> areaResults = new ArrayList<Double>();
		long totalTime = 0;
		
		for(int i=1; i<=NumberOfFiles; i++) {
			final String FILE_NAME = FileName+i+Extension;
			ArrayList<Point> points = (ArrayList<Point>) FileHandler.LoadPoints(SamplesPath, FILE_NAME);
			numberOfPoints.add(points.size());
			long START = 0;
			long ELAPSED_TIME = 0;
			Circle circle = null;
			
			switch(currentAlgorithm) {
			case NAIVE:
				START = System.nanoTime();
				circle = MinCircleAlgorithms.NaiveAlgorithm(points);
				ELAPSED_TIME = System.nanoTime()-START;
				break;
			case WELZL:
				START = System.nanoTime();
				circle = MinCircleAlgorithms.WelzlAlgorithm(points);
				ELAPSED_TIME = System.nanoTime()-START;
				break;
			case RITTER:
				START = System.nanoTime();
				circle = MinCircleAlgorithms.RitterAlgorithm(points);
				ELAPSED_TIME = System.nanoTime()-START;
				break;
			}
			
			timeResults.add(ELAPSED_TIME);
			radiusResults.add(circle.radius);
			areaResults.add(Math.PI*Math.pow(circle.radius,2));
			totalTime += ELAPSED_TIME;
		}
		
		switch(currentAlgorithm) {
		case NAIVE:
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Naive"+"_Radius", radiusResults);
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Naive"+"_Time", timeResults);
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Naive"+"_Area", areaResults);
			System.out.println("Total Time Naive: "+totalTime);
			break;
		case WELZL:
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Welzl"+"_Radius", radiusResults);
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Welzl"+"_Time", timeResults);
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Welzl"+"_Area", areaResults);
			System.out.println("Total Time Welzl: "+totalTime);
			break;
		case RITTER:
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Ritter"+"_Radius", radiusResults);
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Ritter"+"_Time", timeResults);
			FileHandler.WriteResults(ResultsPath, ResultsName+"_Ritter"+"_Area", areaResults);
			System.out.println("Total Time Ritter: "+totalTime);
		}
	}
	
	
	public static void CompareTwoAlgorithms(Algorithm a1, Algorithm a2) {
		ArrayList<Double> percentageResults = new ArrayList<Double>();
		for(int i=1; i<=NumberOfFiles; i++) {
			final String FILE_NAME = FileName+i+Extension;
			ArrayList<Point> points = (ArrayList<Point>) FileHandler.LoadPoints(SamplesPath, FILE_NAME);
			
			final long START_A1;
			final long ELAPSED_A1;
			final long START_A2;
			final long ELAPSED_A2;
			
			START_A1 = System.nanoTime();
			switch(a1) {
			case NAIVE:
				MinCircleAlgorithms.NaiveAlgorithm(points);
				break;
			case WELZL:
				MinCircleAlgorithms.WelzlAlgorithm(points);
				break;
			case RITTER:
				MinCircleAlgorithms.RitterAlgorithm(points);
				break;
			}
			ELAPSED_A1 = System.nanoTime()-START_A1;
			
			START_A2 = System.nanoTime();
			switch(a2) {
			case NAIVE:
				MinCircleAlgorithms.NaiveAlgorithm(points);
				break;
			case WELZL:
				MinCircleAlgorithms.WelzlAlgorithm(points);
				break;
			case RITTER:
				MinCircleAlgorithms.RitterAlgorithm(points);
				break;
			}
			ELAPSED_A2 = System.nanoTime()-START_A2;
			
			double percentage = ((ELAPSED_A1 - ELAPSED_A2) / (double)ELAPSED_A2) * 100;
			percentageResults.add(percentage);
		}
		
		FileHandler.WriteResults(ResultsPath, ResultsName+"_"+a1.name()+"Vs"+a2.name(), percentageResults);
	}
	
	public static void TestCorrectnessOfAlgorithm() {
		double standartDeviation = 0;
		double mean = 0;
		double percentage;
		double intersectionArea;
		double areaOfCircleNaive;
		double areaOfCircle;
		
		for(int i=1; i<=NumberOfFiles; i++) {
			final String FILE_NAME = FileName+i+Extension;
			ArrayList<Point> points = (ArrayList<Point>) FileHandler.LoadPoints(SamplesPath, FILE_NAME);
			Circle circle = null;
			Circle circleNaive = null;
			
			switch(currentAlgorithm) {
			case NAIVE:
				break;
			case WELZL:
				circleNaive = MinCircleAlgorithms.NaiveAlgorithm(points);
				circle = MinCircleAlgorithms.WelzlAlgorithm(points);
				break;
			case RITTER:
				circleNaive = MinCircleAlgorithms.NaiveAlgorithm(points);
				circle = MinCircleAlgorithms.RitterAlgorithm(points);
				break;
			}
			
			// for deviation of circle radius and center uncomment this block
			/* 
			 
			standartDeviation += Math.pow((Math.abs(circleNaive.radius-circle.radius) 
											+ Math.abs(circleNaive.center.x-circle.center.x)
											+ Math.abs(circleNaive.center.y-circle.center.y))/3,2);
			mean += (circleNaive.radius + circleNaive.center.x + circleNaive.center.y)/3;
			
			*/
			
			
			// for deviation of area difference uncomment this block
			/* 
			 
			areaOfCircleNaive = Math.PI*Math.pow(circleNaive.radius, 2);
			areaOfCircle = Math.PI*Math.pow(circle.radius, 2);
			
			standartDeviation += Math.pow(areaOfCircleNaive - areaOfCircle,2);
			mean += areaOfCircleNaive;
			
			 */
			
		}
		
		standartDeviation = Math.sqrt(standartDeviation/NumberOfFiles);
		mean = mean / NumberOfFiles;
		percentage = standartDeviation * 100 / mean ;
		System.out.println("Standart Deviation : " + standartDeviation);
		System.out.println("Deviation Percentage : " + percentage);
		
	}
	
}
