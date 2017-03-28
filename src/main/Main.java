package main;

import algorithms.Centroid;

public class Main {
	public static void main(String[] args) {
		double[][] data = new double[10][3];
		data[0] = new double[] {0, 0, 2};
		data[1] = new double[] {0, 1, 2};
		data[2] = new double[] {0, 2, 2};
		data[3] = new double[] {0, 3, 2};
		data[4] = new double[] {0, 4, 2};
		data[5] = new double[] {0, 5, 2};
		data[6] = new double[] {0, 6, 2};
		data[7] = new double[] {0, 7, 2};
		data[8] = new double[] {0, 8, 2};
		data[9] = new double[] {0, 9, 2};
		
		double[][] centroids = Centroid.determineInitialClusters(data, 5);
		Centroid.printCentroids(centroids);
		
		
		
		
		
		
	}
}
