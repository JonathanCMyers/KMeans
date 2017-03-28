/********************************
 * Author:   Jonathan Myers     *
 * Course:   CSI 5325           *
 * Date:     3/27/2017          *
 ********************************/

package algorithms;

/**
 * Generic Lloyd's Algorithm
 * @author MyersJO
 * @version 1.0 Build 3/27/2017
 */
public class Lloyd extends KMeans {

	public Lloyd(double[][] data, double[][] centroids, int k, int maxIterations) {
		setData(data);
		setCentroids(centroids);
		setK(k);
		setMaxIterations(maxIterations);
	}
	
	public void calcClusters() {
		// Initialize first cluster
		relClusterWeight = new double[k];
		absClusterWeight = new double[k];
		relClusterWeight[0] = 1.0;
		absClusterWeight[0] = data.length;
		
		//double[] newClusterPoint = initializeClusterPoint(samplePoints);
		//clusterPoints[0] = newClusterPoint;
		
		
	}
	
	
	
}
