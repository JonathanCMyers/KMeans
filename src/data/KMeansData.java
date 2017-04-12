/********************************
 * Author:   Jonathan Myers     *
 * Course:   CSI 5325           *
 * Date:     3/27/2017          *
 ********************************/

package data;

/**
 * Contains the data points and describes getters/setters
 * @author MyersJO
 * @version 1.0 Build 3/27/2017
 */
public class KMeansData {
	
	/**
	 * Our data that we are performing the clustering algorithm on
	 */
	private double[][] data;
	
	/**
	 * Centroid that the element is closest to
	 */
	private double[] nearestCentroid;
	
	/**
	 * The mean at each dimension
	 */
	private double[] means;
	
	/**
	 * The standard deviation at each dimension
	 */
	private double[] sds;
	
	/**
	 * The number of dimensions in our data
	 */
	private int dataDimension;
	
	/**
	 * The number of rows at each dimension in our data
	 */
	private int dataLength;
	
	/**
	 * Sets the data and automatically transforms each dimension using the z-score
	 * @param data - two dimensional array that we are performing our clustering algorithm on
	 */
	public KMeansData(double[][] data) {
		setData(data);
		setupNearestCentroids();
		calculateMeansAndStandardDeviations();
		transformData();
	}
	
	/**
	 * Parameter checking for our data
	 * @param data to be parameter checked
	 */
	public void setData(double[][] data) {
		if(data == null) {
			throw new IllegalArgumentException("Data cannot be null!");
		}
		if(data.length > 0) {
			this.data = data;
			dataLength = data.length;
			dataDimension = data[0].length;
		} else {
			throw new IllegalArgumentException("Data length cannot be zero.");
		}
	}
	
	/**
	 * Initializes every data point's nearest centroid to -1
	 */
	private void setupNearestCentroids() {
		nearestCentroid = new double[dataLength];
		for(int i = 0; i < dataLength; i++) {
			nearestCentroid[i] = -1;
		}
	}
	
	/**
	 * Calculates the mean and standard deviation of each dimension
	 */
	private void calculateMeansAndStandardDeviations() {

		// Allocate data
		means = new double[dataDimension];
		sds = new double[dataDimension];
		
		// Calculate means
		for(int col = 0; col < dataDimension; col++) {
			means[col] = 0;
			for(int row = 0; row < dataLength; row++) {
				means[col] += data[row][col];
			}
			means[col] = means[col]/dataLength;
		}
		
		// Calculate standard deviations
		for(int col = 0; col < dataDimension; col++) {
			sds[col] = 0;
			for(int row = 0; row < dataLength; row++) {
				sds[col] += Math.pow((data[row][col] - means[col]), 2);
			}
			sds[col] = sds[col]/dataLength;
			sds[col] = Math.pow(sds[col], .5);
		}
		
	}
	
	/**
	 * Turn each observation into a z-score using its mean & standard deviation
	 */
	private void transformData() {
		for(int col = 0; col < dataDimension; col++) {
			for(int row = 0; row < dataLength; row++) {
				data[row][col] = (data[row][col] - means[col]) / sds[col];
			}
		}
	}
	
	/**
	 * Returns the row at element i
	 * @param i - the row to return data from
	 * @return the row at element i
	 */
	public double[] getRow(int i) {
		return data[i];
	}
	
	/**
	 * Returns the column at dimension col
	 * @param col - the column to return data from
	 * @return the column at element col
	 */
	public double[] getColumn(int col) {
		double[] colData = new double[dataLength];
		for(int row = 0; row < dataLength; row++) {
			colData[row] = data[row][col];
		}
		return colData;
	}
	
	/**
	 * Returns all of the data
	 * @return double[][] containing the data
	 */
	public double[][] getData() {
		return data;
	}
	
	/**
	 * Returns the array containing the nearest centroid for each data point
	 * @return double[] with length <dataLength> containing the nearest centroid to each element
	 */
	public double[] getNearestCentroids() {
		return nearestCentroid;
	}
	
	/**
	 * Returns the number of dimensions in our data
	 * @return dimension count
	 */
	public int getDataDimension() {
		return dataDimension;
	}
	
	/**
	 * Returns the number of rows in each observation in our data
	 * @return nrow(data)
	 */
	public int getDataLength() {
		return dataLength;
	}
	
	/**
	 * Sets the nearest centroids for each data point
	 * @param nearestCentroid - the nearest centroid for each data point
	 * @throws IllegalArgumentException if the length of the input array is not the same as <dataLength>
	 */
	public void setNearestCentroids(double[] nearestCentroid) throws IllegalArgumentException {
		if(nearestCentroid.length != dataLength) {
			throw new IllegalArgumentException("Length of nearest centroids is not equal to the data length.");
		}
		this.nearestCentroid = nearestCentroid;
	}
}
