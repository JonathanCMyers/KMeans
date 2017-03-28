/********************************
 * Author:   Jonathan Myers     *
 * Course:   CSI 5325           *
 * Date:     3/27/2017          *
 ********************************/

package algorithms;

/**
 * Abstract class that the other algorithms should extend - contains getters, setters, and common data
 * @author MyersJO
 * @version 1.0 Build 3/27/2017
 */
public abstract class KMeans {
	
	protected double[][] data;
	protected double[][] centroids;
	
	protected int k;
	
	protected int maxIterations;
	
	protected int currentIteration;
	
	protected double distortion;
	
	protected int dataDimension;
	
	protected double[] relClusterWeight;
	protected double[] absClusterWeight;
	
	public abstract void calcClusters();
	
	public void setData(double[][] data) {
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null!");
		}
		if(data.length > 0) {
			this.data = data;
			dataDimension = data[0].length;
		} else {
			throw new IllegalArgumentException("Data length cannot be zero.");
		}
	}
	
	public void setCentroids(double[][] centroids) {
		this.centroids = centroids;
	}
	
	public void setDistortion(double distortion) {
		this.distortion = distortion;
	}
	
	public void setK(int k) {
		this.k = k;
	}
	
	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}
	
	public double[][] getCentroids() {
		return centroids;
	}
	
	public double getDistortion() {
		return distortion;
	}
	
	public int getK() {
		return k;
	}
	
	public int getMaxIterations() {
		return maxIterations;
	}
	
	public int getCurrentIteration() {
		return currentIteration;
	}
	
	
}
