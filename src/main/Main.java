package main;

import data.Centroid;
import data.KMeansData;

public class Main {
	public static void main(String[] args) {
		double[][] xdata = new double[10][3];
		xdata[0] = new double[] {0, 0, 2};
		xdata[1] = new double[] {0, 1, 2};
		xdata[2] = new double[] {0, 2, 2};
		xdata[3] = new double[] {0, 3, 2};
		xdata[4] = new double[] {0, 4, 2};
		xdata[5] = new double[] {0, 5, 2};
		xdata[6] = new double[] {0, 6, 2};
		xdata[7] = new double[] {0, 7, 2};
		xdata[8] = new double[] {0, 8, 2};
		xdata[9] = new double[] {0, 9, 2};
		KMeansData data = new KMeansData(xdata);
		
		Centroid centroid = Centroid.determineInitialClusters(data, 5);
		System.out.println(centroid);
		
	}
}
