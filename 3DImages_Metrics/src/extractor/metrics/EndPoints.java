package extractor.metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import extractor.utils.Neighborhood;

/**
 * This class, the extraction and counting of the number of end points of the image is performed. 
 * The count is performed based on the amount of neighbors of a voxel of the image. The neighbor check 
 * function is responsible for identifying the neighbors of a given voxel.
 * 
 * @author Misael Jr
 *
 */

public class EndPoints {

	ArrayList<Double[]> endpoints = new ArrayList<Double[]>();

	ArrayList<Double[]> neighbors;
	ArrayList<Double[]> visitedTemp;

	Neighborhood neighbor;

	/**
	 * Function that measures the number of endpoints
	 * @param data -- Matrix with image points.
	 * @param nx -- Numbers of points on the x-axis
	 * @param ny -- Numbers of points on the y-axis
	 * @param nz -- Numbers of points on the z-axis
	 * @param dim -- Image size
	 * @return Number of image segments
	 * @throws FileNotFoundException
	 */
	
	public void extratorCountBifurc(double[][][][] data, int nx, int ny, int nz, int dim) throws FileNotFoundException{
		int quantTerm = 0;
		neighbor = new Neighborhood();
		//double endpoints_normalized = 0;

		for (int d = 0; d < dim; d++){
			for (int i = 0; i < nx; i++) {
				for (int j = 0; j < ny; j++){
					for (int k = 0; k < nz; k++){

						if (data[i][j][k][d] != 0){

							Double[] points = {(double) i, (double) j, (double) k};

							neighbors = neighbor.checkNeighbors26(data, endpoints, i, j, k, d, nx, ny, nz, dim);

							if (neighbors.size() == 1){
								quantTerm++;
								endpoints.add(points); 
							}else{
								continue;
							}
						}else
							continue;
					}
				}
			}
		}

		//endpoints_normalized = getNormalized(quantBifurc);
		
		System.out.println("###########Endpoints Extractor#########");
		System.out.println("Total amount of endpoints: " +quantTerm);

	}

	/**
	 * set the normalization according to the base of max and min
	 * @param endpoints
	 * @return Normalized value
	 */
	/*public double getNormalized(double endpoints){

		double value_normal = 0;

		value_normal = (endpoints - min)/(max - min);

		return value_normal;

	}*/

}
