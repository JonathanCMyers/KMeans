package algorithms;

import data.Centroid;

public abstract class Algorithm {
	
	protected int maxIterations;
	
	protected int currentIteration;
	
	protected double distortion;
	
	protected int k;
	
	protected String algorithmName;
	
	protected Centroid centroid;
	/*
	protected double[] relClusterWeight;
	protected double[] absClusterWeight;
	*/
	
	//protected double[][] centroids;
	
	public abstract void calcClusters(double[][] data);
	
	
	public void setCentroids(Centroid centroid) {
		if(centroid == null) {
			throw new IllegalArgumentException("Centroid cannot be null.");
		}
		this.centroid = centroid;
	}
	
	public void setDistortion(double distortion) {
		this.distortion = distortion;
	}
	
	public void setK(int k) {
        if (k < 2) {
            throw new IllegalArgumentException("Invalid number of clusters: " + k);
        }
		this.k = k;
	}
	
	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}
	
	/*
	
	public double[][] getCentroids() {
		return centroids;
	}
	*/
	
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
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("Algorithm used: %s", algorithmName));
        sb.append(String.format("K-Means distortion: %.5f%n", distortion));
        
        return sb.toString();
    }
    
    /**
     * Computes the squared distance between two equally-sized input vectors
     * @param x - first input vector
     * @param y - second input vector
     * @return the squared distance between the two input vectors
     * @throws IllegalArgumentException if the lengths of the inputs differ
     */
    public static double squaredDistance(double[] x, double[] y) throws IllegalArgumentException {
    	if(x.length != y.length) {
    		throw new IllegalArgumentException("Problem in SquaredDistance: lengths of inputs differ.");
    	}
    	double dist = 0;
    	for(int i = 0; i < x.length; i++) {
    		dist += (x[i] - y[i])*(x[i] - y[i]);
    	}
    	return Math.pow(dist, .5);
    }
	
}
