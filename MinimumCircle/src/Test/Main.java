package Test;

import Test.Tester.Algorithm;

public class Main {

	public static void main(String[] args) {
		Tester.NumberOfFiles = 1664;
		Tester.SamplesPath = "src/Samples";
		Tester.FileName = "Test-";
		Tester.Extension = ".points";
		Tester.ResultsPath = "src/Results";
		Tester.ResultsName = "Results";
		
		
		////////// Time and Radius Tests //////////
		//Tester.currentAlgorithm = Tester.Algorithm.NAIVE;
		//Tester.TestAlgorithm();
		//Tester.currentAlgorithm = Tester.Algorithm.WELZL;
		//Tester.TestAlgorithm();
		//Tester.currentAlgorithm = Tester.Algorithm.RITTER;
		//Tester.TestAlgorithm();
		
		
		////////// Algorithm Comparisons //////////
		//Tester.CompareTwoAlgorithms(Algorithm.NAIVE, Algorithm.WELZL);
		//Tester.CompareTwoAlgorithms(Algorithm.NAIVE, Algorithm.RITTER);
		//Tester.CompareTwoAlgorithms(Algorithm.WELZL, Algorithm.RITTER);
		
		
		////////// Error Percentages Test //////////
		//Tester.currentAlgorithm = Tester.Algorithm.WELZL;
		//Tester.TestCorrectnessOfAlgorithm();
		//Tester.currentAlgorithm = Tester.Algorithm.RITTER;
		//Tester.TestCorrectnessOfAlgorithm();
	}

}
