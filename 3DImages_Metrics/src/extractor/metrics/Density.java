package extractor.metrics;

import java.io.FileNotFoundException;

/**
 * This class, the value of the image density is measured. To calculate the density is 
 * measured the number of voxels in the blood vessel by the number of voxels of the entire image.
 * 
 * @author Misael Jr
 *
 */

public class Density {

	/**
	 * // Function that measures the density of the image (amount of voxels of the blood vessel / amount of voxels of the whole image)
	 * @param data -- Matrix with image points.
	 * @param nx -- Numbers of points on the x-axis
	 * @param ny -- Numbers of points on the y-axis
	 * @param nz -- Numbers of points on the z-axis
	 * @param dim -- Image size
	 * @return Density value
	 * @throws FileNotFoundException
	 */
	public double extractorDensi(double[][][][] data, int nx, int ny, int nz, int dim) throws FileNotFoundException{
		double countVoxelImg = 0;
		double totalVoxelImg = 0;
		double countVoxelVaso = 0;
		double dens = 0;
		//double dens_normalized = 0;

		for (int d = 0; d < dim; d++)
			for (int i = 0; i < nx; i++) 
				for (int j = 0; j < ny; j++)
					for (int k = 0; k < nz; k++){
						if (data[i][j][k][d] != 0){
							countVoxelVaso++;
						}else{
							countVoxelImg++;
						}	
					}
		
		totalVoxelImg = countVoxelImg + countVoxelVaso;
		dens = Math.atan2(countVoxelVaso, totalVoxelImg);
		
		//dens_normalized = getNormalized(dens);
		
		System.out.println("###########Density Extractor###########");
		System.out.println("Density: " +dens);
		
		return dens;
		
	}
	
	/**
	 * set the normalization according to the base of max and min
	 * @param desnsity
	 * @return Normalized value
	 */
	/*public double getNormalized(double density){
		
		double valor_normal = 0;
		
		valor_normal = (density - min)/(max - min);
		
		return valor_normal;
		
	}*/

}
