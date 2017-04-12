/********************************
 * Author:   Jonathan Myers     *
 * Course:   CSI 5325           *
 * Date:     3/27/2017          *
 ********************************/

package data;

import java.util.Arrays;

/**
 * Contains functions relevant to centroid placement
 * @author MyersJO
 * @version 1.0 Build 4/11/2017
 */
public class Centroid {
	
	/**
	 * Centroid matrix
	 */
	private double[][] centroid;
	
	/**
	 * Number of dimensions in the centroids
	 */
	private int numDimensions;
	
	/**
	 * Initializes a Centroid object
	 * @param centroid - centroid matrix
	 */
	public Centroid(double[][] centroid) {
		setCentroid(centroid);
		numDimensions = centroid[0].length;
	}
	
	/**
	 * Parameter checking for centroids
	 * @param centroid - centroid matrix
	 */
	public void setCentroid(double[][] centroid) {
		this.centroid = centroid;
	}
	
	/**
	 * Determines initial clusters (in a non-optimal way) just to maintain consistency across all algorithms
	 * @param data - Starting data 
	 * @param k - number of centroids
	 * @return centroids - deterministically chosen starting centers
	 */
	public static Centroid determineInitialClusters(KMeansData data, int k) {
		
		if(k > data.getDataLength()) {
			throw new IllegalArgumentException("More clusters than there are data points.");
		}
		
		double[][] centroids = new double[k][];
		
		for(int row = 0; row < k; row++) {
			centroids[row] = data.getRow(row * (data.getDataLength())/k);
		}
		
		return new Centroid(centroids);
	}
	
	/**
	 * Gets the number of centroids being used, k
	 * @return the number of centroids, k
	 */
	public int getLength() {
		return centroid.length;
	}
	
	public double[] getRow(int row) {
		return centroid[row];
	}
	
	public double[] getCol(int col) {
		double[] colData = new double[centroid.length];
		for(int row = 0; row < centroid.length; row++) {
			colData[row] = centroid[row][col];
		}
		return colData;
	}
	
	public void setRow(int row, double[] rowData) {
		if(rowData == null) {
			throw new IllegalArgumentException("Cannot set a null row.");
		}
		centroid[row] = rowData;
	}
	
	public void setReplicatedRow(int row, double d) {
		double[] rowData = new double[numDimensions];
		Arrays.fill(rowData, d);
		centroid[row] = rowData;
	}
	
	public double getDataPoint(int row, int col) {
		return centroid[row][col];
	}
	
	public void setDataPoint(int row, int col, double d) {
		centroid[row][col] = d;
	}
	
	public void incrementDataPoint(int row, int col, double d) {
		centroid[row][col] += d;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(double[] currentCentroid : centroid) {
			sb.append(" ");
			for(double d : currentCentroid) {
				sb.append(d + " ");
			}
		}
		return sb.toString();
	}
}
