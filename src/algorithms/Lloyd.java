/********************************
 * Author:   Jonathan Myers     *
 * Course:   CSI 5325           *
 * Date:     3/27/2017          *
 ********************************/

package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import data.Centroid;
import data.KMeansData;
import smile.util.MulticoreExecutor;

/**
 * Generic Lloyd's Algorithm
 * @author MyersJO
 * @version 1.0 Build 3/27/2017
 */
public class Lloyd extends Algorithm {

	public Lloyd(Centroid centroid, int k, int maxIterations) {
		algorithmName = "Lloyd's Algorithm";
		setCentroids(centroid);
		setK(k);
		setMaxIterations(maxIterations);
	}
	
	/*
	
	public void calcClusters(double[][] data) {
		
		
	}
	
	*/
	
	
	
	/**
	 * Allows for Lloyds Algorithm to be run in a ThreadPool
	 */
    static class LloydThread implements Callable<Double> {

        /**
         * The start index of data portion for this task.
         */
        final int start;
        
        /**
         * The end index of data portion for this task.
         */
        final int end;
        
        /**
         * 
         */
        final KMeansData data;
        final int k;
        final Centroid centroid;
        int[] y;

        LloydThread(KMeansData data, Centroid centroid, int[] y, int start, int end) {
            this.data = data;
            this.k = centroid.getLength();
            this.y = y;
            this.centroid = centroid;
            this.start = start;
            this.end = end;
        }

        @Override
        public Double call() {
            double wcss = 0.0;
            for (int i = start; i < end; i++) {
                double nearest = Double.MAX_VALUE;
                for (int j = 0; j < k; j++) {
                    double dist = squaredDistance(data.getRow(i), centroid.getRow(j));
                    if (nearest > dist) {
                        y[i] = j;
                        nearest = dist;
                    }
                }
                wcss += nearest;
            }
            
            return wcss;
        }
    }
    
    public void calculateClusters(KMeansData data, int maxIter) {
    	if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);
        }
    	int nrow = data.getDataLength();
    	int d = data.getDataDimension();
    	
    	//int[][]
    			
    	double distortion = Double.MAX_VALUE;
    	
    	int[] size = new int[k]; // Number of elements associated with each cluster
    	Centroid centroid = Centroid.determineInitialClusters(data, k);
    	
    	// QUESTION MARK ON THESE TWO VARIABLES
    	int[][] nd = new int[k][d]; // The number of non-missing values per cluster per variable.
    	//int[] y = seed(data, k, ClusteringDistance.EUCLIDEAN_MISSING_VALUES);
    	int[] y = new int[nrow];
    	Arrays.fill(y, -1);
    	
    	int numProcessors = MulticoreExecutor.getThreadPoolSize();
    	
    	
    	List<LloydThread> tasks = null;
        if (nrow >= 1000 && numProcessors >= 2) {
            tasks = new ArrayList<>(numProcessors + 1);
            int step = nrow / numProcessors;

            int start = 0;
            int end = step;
            for (int i = 0; i < numProcessors - 1; i++) {
                tasks.add(new LloydThread(data, centroid, y, start, end));
                start += step;
                end += step;
            }
            tasks.add(new LloydThread(data, centroid, y, start, nrow));
        }
        
        for (int iter = 0; iter < maxIter; iter++) {
            Arrays.fill(size, 0);
            for (int i = 0; i < k; i++) {
            	centroid.setReplicatedRow(i, 0);
                Arrays.fill(nd[i], 0);
            }

            for (int row = 0; row < nrow; row++) {
            	double[] rowData = data.getRow(row);
                int m = y[row]; // m is the closest centroid for data point @ row
                size[m]++; // number of centroid neighbors increases by 1
                for (int col = 0; col < d; col++) {
                    if (!Double.isNaN(rowData[col])) {
                    	centroid.incrementDataPoint(m, col, rowData[col]);
                        nd[m][col]++;
                    }
                }
            }

            for (int currentCentroid = 0; currentCentroid < k; currentCentroid++) {
                for (int col = 0; col < d; col++) {
                	centroid.setDataPoint(currentCentroid, col, 
                			centroid.getDataPoint(currentCentroid, col)/nd[currentCentroid][col]);
                    //Basically the same as: centroids[i][j] /= nd[i][j];
                }
            }

            double wcss = Double.NaN;
            if (tasks != null) {
                try {
                    wcss = 0.0;
                    for (double ss : MulticoreExecutor.run(tasks)) {
                        wcss += ss;
                    }
                } catch (Exception ex) {
                	System.err.println("Failed to run K-Means on multi-core: " + ex.getMessage());

                    wcss = Double.NaN;
                }
            }

            if (Double.isNaN(wcss)) {
                wcss = 0.0;
                for (int i = 0; i < n; i++) {
                    double nearest = Double.MAX_VALUE;
                    for (int j = 0; j < k; j++) {
                        double dist = squaredDistance(data[i], centroids[j]);
                        if (nearest > dist) {
                            y[i] = j;
                            nearest = dist;
                        }
                    }
                    wcss += nearest;
                }              
            }
            
            if (distortion <= wcss) {
                break;
            } else {
                distortion = wcss;
            }
        }
    }
	
	
    
    public static KMeans lloyd(double[][] data, int k, int maxIter) {

        

        

        // In case of early stop, we should recalculate centroids and clusterSize.
        Arrays.fill(size, 0);
        for (int i = 0; i < k; i++) {
            Arrays.fill(centroids[i], 0);
            Arrays.fill(nd[i], 0);
        }

        for (int i = 0; i < n; i++) {
            int m = y[i];
            size[m]++;
            for (int j = 0; j < d; j++) {
                if (!Double.isNaN(data[i][j])) {
                    centroids[m][j] += data[i][j];
                    nd[m][j]++;
                }
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < d; j++) {
                centroids[i][j] /= nd[i][j];
            }
        }

        KMeans kmeans = new KMeans();
        kmeans.k = k;
        kmeans.distortion = distortion;
        kmeans.size = size;
        kmeans.centroids = centroids;
        kmeans.y = y;

        return kmeans;
    }
    
    
    
    
    
    
    
    
    
	
}
