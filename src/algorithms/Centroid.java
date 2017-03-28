/********************************
 * Author:   Jonathan Myers     *
 * Course:   CSI 5325           *
 * Date:     3/27/2017          *
 ********************************/

package algorithms;

/**
 * Contains functions relevant to centroid placement
 * @author MyersJO
 * @version 1.0 Build 3/27/2017
 */
public class Centroid {
	
	/**
	 * Determines initial clusters (in a non-optimal way) just to maintain consistency across all algorithms
	 * @param data - Starting data 
	 * @param k - number of centroids
	 * @return centroids - deterministically chosen starting centers
	 */
	public static double[][] determineInitialClusters(double[][] data, int k) {
		
		if(k > data.length) {
			throw new IllegalArgumentException("More clusters than there are data points.");
		}
		
		double[][] centroids = new double[k][];
		
		for(int i = 0; i < k; i++) {
			centroids[i] = data[i * (data.length)/k];
		}
		
		return centroids;
	}
	
	public static void printCentroids(double[][] centroids) {
		for(double[] centroid : centroids) {
			System.out.println(" ");
			for(double d : centroid) {
				System.out.print(d + " ");
			}
		}
	}
}
