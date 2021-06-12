package File;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import Geometry.Point;

public class FileHandler {
	public static List<Point> LoadPoints(String path, String filename){
		ArrayList<Point> points = new ArrayList<Point>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(path,filename));
			while(scanner.hasNextInt()) {
				Point p = new Point(scanner.nextInt(),scanner.nextInt());
				points.add(p);
			}
			
			scanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
		
		return points;
	}
	
	static public<T> void WriteResults(String path, String filename, List<T> results ) {
		File resultFile = null;
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		
		try {
			resultFile = new File(path, filename);
			fileWriter = new FileWriter(resultFile);
	        writer = new BufferedWriter(fileWriter);
	        
	        StringBuilder data = new StringBuilder();
	        for(int i = 0; i<results.size(); i++) {
	        	data.append(i+" "+results.get(i)+"\n");
	        }
	        
	        writer.write(data.toString());
	        writer.close();
	        fileWriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
