package extractor.utils;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is conducted the search, identification and neighbors count each point (voxel) of the image. 
 * Through these metrics, the image is divided into segments.
 *
 * @author Misael Jr
 *
 */
public class Neighborhood {

	ArrayList<Double[]> visitedTemp; //Temporary Array to store visited points
	
	/**
	 * Responsible function in verifying the neighbors of a certain current point of the image.
	 * 
	 * @param data -- Matrix with image points. 
	 * @param pontos_bifurc -- Array with the bifurcation points identified in the image.
	 * @param x -- Points on the x-axis
	 * @param y -- Points on the y-axis
	 * @param z -- Points on the z-axis
	 * @param d - -Image size
	 * @param nx -- Numbers of points on the x-axis
	 * @param ny -- Numbers of points on the y-axis
	 * @param nz -- Numbers of points on the z-axis
	 * @param dim -- Image size
	 * @return Number of neighbors of current point
	 */
	public ArrayList<Double[]> checkNeighbors26(double[][][][] data, ArrayList<Double[]> bifurc_points, 
			int x, int y, int z, int d, int nx, int ny, int nz, int dim){
		
		visitedTemp = new ArrayList<Double[]>();
		
		if (x !=0 && y !=0 && z !=0)
			if (data[x-1][y-1][z-1][d] > 0.0){
				Double[] points1 = new Double[3];
				points1[0] = x-1.0;
				points1[1] = y-1.0;
				points1[2] = z-1.0;
				visitedTemp.add(points1);
			}

		if (x !=0 && z !=0)
			if (data[x-1][y][z-1][d] > 0.0){
				Double[] points2 = new Double[3];
				points2[0] = x-1.0;
				points2[1] = (double) y;
				points2[2] = z-1.0;
				visitedTemp.add(points2);
			}

		if (x !=0 && y < ny-1 && z !=0)
			if (data[x-1][y+1][z-1][d] > 0.0){
				Double[] points3 = new Double[3];
				points3[0] = x-1.0;
				points3[1] = y+1.0;
				points3[2] = z-1.0;
				visitedTemp.add(points3);
			}

		if (y !=0 && z !=0)
			if (data[x][y-1][z-1][d] > 0.0){
				Double[] points4 = new Double[3];
				points4[0] = (double) x;
				points4[1] = y-1.0;
				points4[2] = z-1.0;
				visitedTemp.add(points4);
			}

		if (z !=0)
			if (data[x][y][z-1][d] > 0.0){
				Double[] points5 = new Double[3];
				points5[0] = (double) x;
				points5[1] = (double) y;
				points5[2] = z-1.0;
				visitedTemp.add(points5); 
			}

		if (y < ny-1 && z !=0)
			if (data[x][y+1][z-1][d] > 0.0){
				Double[] points6 = new Double[3];
				points6[0] = (double) x;
				points6[1] = y+1.0;
				points6[2] = z-1.0;
				visitedTemp.add(points6); 
			}

		if (x < nx-1 && y !=0 && z !=0)
			if (data[x+1][y-1][z-1][d] > 0.0){
				Double[] points7 = new Double[3];
				points7[0] = x+1.0;
				points7[1] = y-1.0;
				points7[2] = z-1.0;
				visitedTemp.add(points7); 
			}

		if (x < nx-1 && z !=0)
			if (data[x+1][y][z-1][d] > 0.0){
				Double[] points8 = new Double[3];
				points8[0] = x+1.0;
				points8[1] = (double) y;
				points8[2] = z-1.0;
				visitedTemp.add(points8); 
			}

		if (x < nx-1 && y < ny-1 && z !=0)
			if (data[x+1][y+1][z-1][d] > 0.0){
				Double[] points9 = new Double[3];
				points9[0] = x+1.0;
				points9[1] = y+1.0;
				points9[2] = z-1.0;
				visitedTemp.add(points9); 
			}

		if (x !=0 && y !=0)
			if (data[x-1][y-1][z][d] > 0.0){
				Double[] points10 = new Double[3];
				points10[0] = x-1.0;
				points10[1] = y-1.0;
				points10[2] = (double) z;
				visitedTemp.add(points10); 
			}

		if (x !=0)
			if (data[x-1][y][z][d] > 0.0){
				Double[] points11 = new Double[3];
				points11[0] = (double) x-1;
				points11[1] = (double) y;
				points11[2] = (double) z;
				visitedTemp.add(points11); 
			}

		if (x !=0 && y < ny - 1)
			if (data[x-1][y+1][z][d] > 0.0){
				Double[] points12 = new Double[3];
				points12[0] = x-1.0;
				points12[1] = y+1.0;
				points12[2] = (double) z;
				visitedTemp.add(points12); 
			}

		if (y !=0)
			if (data[x][y-1][z][d] > 0.0){
				Double[] points13 = new Double[3];
				points13[0] = (double) x;
				points13[1] = y-1.0;
				points13[2] = (double) z;
				visitedTemp.add(points13); 
			}

		if (y < ny - 1)
			if (data[x][y+1][z][d] > 0.0){
				Double[] points14 = new Double[3];
				points14[0] = (double) x;
				points14[1] = y+1.0;
				points14[2] = (double) z;
				visitedTemp.add(points14); 
			}

		if (x < nx-1 && y != 0)
			if (data[x+1][y-1][z][d] > 0.0){
				Double[] points15 = new Double[3];
				points15[0] = x+1.0;
				points15[1] = y-1.0;
				points15[2] = (double) z;
				visitedTemp.add(points15); 
			}

		if (x < nx-1)
			if (data[x+1][y][z][d] > 0.0){
				Double[] points16 = new Double[3];
				points16[0] = (double) x+1;
				points16[1] = (double) y;
				points16[2] = (double) z;
				visitedTemp.add(points16); 
			}

		if (x < nx-1 && y < ny-1)
			if (data[x+1][y+1][z][d] > 0.0){
				Double[] points17 = new Double[3];
				points17[0] = x+1.0;
				points17[1] = y+1.0;
				points17[2] = (double) z;
				visitedTemp.add(points17); 
			}

		if (x !=0 && y !=0 && z < nz-1)
			if (data[x-1][y-1][z+1][d] > 0.0){
				Double[] points18 = new Double[3];
				points18[0] = x-1.0;
				points18[1] = y-1.0;
				points18[2] = z+1.0;
				visitedTemp.add(points18); 
			}

		if (x !=0 && z < nz-1)
			if (data[x-1][y][z+1][d] > 0.0){
				Double[] points19 = new Double[3];
				points19[0] = x-1.0;
				points19[1] = (double) y;
				points19[2] = z+1.0;
				visitedTemp.add(points19); 
			}

		if (x !=0 && y < ny - 1 && z < nz-1)
			if (data[x-1][y+1][z+1][d] > 0.0){
				Double[] points20 = new Double[3];
				points20[0] = x-1.0;
				points20[1] = y+1.0;
				points20[2] = z+1.0;
				visitedTemp.add(points20); 
			}

		if (y !=0 && z < nz-1)
			if (data[x][y-1][z+1][d] > 0.0){
				Double[] points21 = new Double[3];
				points21[0] = (double) x;
				points21[1] = y-1.0;
				points21[2] = z+1.0;
				visitedTemp.add(points21); 
			}

		if (z < nz - 1)
			if (data[x][y][z+1][d] > 0.0){
				Double[] points22 = new Double[3];
				points22[0] = (double) x;
				points22[1] = (double) y;
				points22[2] = z+1.0;
				visitedTemp.add(points22); 
			}

		if (y < ny-1 && z < nz-1)
			if (data[x][y+1][z+1][d] > 0.0){
				Double[] points23 = new Double[3];
				points23[0] = (double) x;
				points23[1] = y+1.0;
				points23[2] = z+1.0;
				visitedTemp.add(points23); 
			}

		if (x < nx-1 && y != 0 && z < nz-1)
			if (data[x+1][y-1][z+1][d] > 0.0){
				Double[] points24 = new Double[3];
				points24[0] = x+1.0;
				points24[1] = y-1.0;
				points24[2] = z+1.0;
				visitedTemp.add(points24); 
			}

		if (x < nx-1 && z < nz-1)
			if (data[x+1][y][z+1][d] > 0.0){
				Double[] points25 = new Double[3];
				points25[0] = x+1.0;
				points25[1] = (double) y;
				points25[2] = z+1.0;
				visitedTemp.add(points25); 
			}

		if (x < nx-1 && y < ny-1 && z < nz-1)
			if (data[x+1][y+1][z+1][d] > 0.0){
				Double[] points26 = new Double[3];
				points26[0] = x+1.0;
				points26[1] = y+1.0;
				points26[2] = z+1.0;
				visitedTemp.add(points26); 
			}

		// if any visitor's point of the visitedTemp array is also present in the bifurc_points array, then that point
		// should be excluded from visitedTemp. For it is a redundancy, since the same point is present in two bifurcations.

		if(visitedTemp.size() > 2)
			for(int i = 0; i < visitedTemp.size();i++){
				Double[] i1 = visitedTemp.get(i);
				for(int j = 0; j < bifurc_points.size();j++){
					Double[] i2 = bifurc_points.get(j);

					if(Arrays.equals(i1, i2)){
						visitedTemp.remove(i);
					}

				}
			}
		
		return visitedTemp;

	}
}
