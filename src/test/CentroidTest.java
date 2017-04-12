package test;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Centroid;

public class CentroidTest {
	
	@Test
	public void testGetRow() {
		double[][] centroidData = new double[3][];
		centroidData[0] = new double[] {1, 2, 3};
		centroidData[1] = new double[] {4, 5, 6};
		centroidData[2] = new double[] {7, 8, 9};
		Centroid centroid = new Centroid(centroidData);
		double[] row = centroid.getRow(1);
		for(int i = 0; i < 3; i++) {
			assertEquals(centroidData[1][i], row[i], 1e-15);
		}
	}
	
	@Test
	public void testSetRow() {
		double[][] centroidData = new double[3][];
		centroidData[0] = new double[] {1, 2, 3};
		centroidData[1] = new double[] {4, 5, 6};
		centroidData[2] = new double[] {7, 8, 9};
		Centroid centroid = new Centroid(centroidData);
		double[] row = new double[] {0, 0, 0};
		centroid.setRow(1, row);
		for(int i = 0; i < 3; i++) {
			assertEquals(centroidData[1][i], 0, 1e-15);
		}
	}
	
	@Test
	public void testSetReplicatedRow() {
		double[][] centroidData = new double[3][];
		centroidData[0] = new double[] {1, 2, 3};
		centroidData[1] = new double[] {4, 5, 6};
		centroidData[2] = new double[] {7, 8, 9};
		Centroid centroid = new Centroid(centroidData);
		centroid.setReplicatedRow(1, 0);
		for(int i = 0; i < 3; i++) {
			assertEquals(centroidData[1][i], 0, 1e-15);
		}
	}
	
	@Test
	public void testSetDataPoint() {
		double[][] centroidData = new double[4][];
		centroidData[0] = new double[] {1, 2};
		centroidData[1] = new double[] {3, 4};
		centroidData[2] = new double[] {5, 6};
		centroidData[3] = new double[] {7, 8};
		Centroid centroid = new Centroid(centroidData);
		centroid.setDataPoint(2, 0, 17);
		assertEquals(centroid.getRow(2)[0], 17, 1e-15);
	}

}
