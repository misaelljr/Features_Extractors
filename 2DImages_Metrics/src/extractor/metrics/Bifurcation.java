package extractor.metrics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class the extraction and counting of the amount of bifurcation of the image is performed. 
 * The count is performed based on the amount of neighbors of a pixel of the image. The neighbor check function is 
 * responsible for identifying the neighbors of a given pixel.
 * 
 * @author Misael Jr
 *
 */

public class Bifurcation {

	ArrayList<Double[]> neighbors;

	ArrayList<Double[]> points_bifurc = new ArrayList<Double[]>();
	ArrayList<Double[]> points_endpoints = new ArrayList<Double[]>();

	Count_Segments countSeg;
	
	ArrayList<Double[]> visitedTemp;


	public void countBifurc(BufferedImage image, int w, int h) throws FileNotFoundException{

		countSeg =  new Count_Segments();
		int quantBifurc = 0;

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {

				if(image.getRGB(i, j) == -1){

					Double[] pontos = {(double) i, (double) j};

					neighbors = checkVizinhanca(image, points_bifurc, i, j, w, h);

					if(neighbors.size() > 2){
						quantBifurc++;
						points_bifurc.add(pontos); // if it is bifurcation point, add in the array of bifurcation points
					}else if(neighbors.size() == 1){
						points_endpoints.add(pontos);
					}else{
						continue;

					}
				}
			}
		}

		System.out.println("###########Extactor Quant Bifurcations#########");
		System.out.println("Total amount of bifurcations: " +quantBifurc);
		countSeg.extratorCountSeg(image, points_bifurc, points_endpoints, w, h);
	}
	
	/**
	 * Responsible function in verifying the neighbors of a certain current point of the image.
	 * 
	 * @param image -- Object with image.
	 * @param pontos_bifurc -- Array with the bifurcation points identified in the image.
	 * @param x -- Points on the x-axis
	 * @param y -- Points on the y-axis
	 * @param w -- Numbers of points on the x-axis
	 * @param h -- Numbers of points on the y-axis
	 * @return Number of neighbors of current point
	 */
	
	public ArrayList<Double[]> checkVizinhanca(BufferedImage imagem, ArrayList<Double[]> points_bifurc, int x, int y, int w, int h) {

		visitedTemp = new ArrayList<Double[]>();

		//Caso o vizinho seja um ponto da imagem ( = -1) ele é add no array visitadosTemp

		if(x < w - 1)
			if (imagem.getRGB(x + 1, y) == -1){
				Double[] pontos1 = new Double[2];
				pontos1[0] = x+1.0;
				pontos1[1] = (double) y;
				visitedTemp.add(pontos1);
			}

		if(x < w - 1 && y < h - 1)
			if (imagem.getRGB(x + 1, y + 1) == -1){ 
				Double[] pontos2 = new Double[2];
				pontos2[0] = x+1.0;
				pontos2[1] = y+1.0;
				visitedTemp.add(pontos2);
			}

		if(y < h - 1)
			if (imagem.getRGB(x, y + 1) == -1){
				Double[] pontos3 = new Double[2];
				pontos3[0] = (double) x;
				pontos3[1] = y+1.0;
				visitedTemp.add(pontos3);
			}

		if(x != 0 && y < h - 1)
			if (imagem.getRGB(x - 1, y + 1) == -1){ 
				Double[] pontos4 = new Double[2];
				pontos4[0] = x-1.0;
				pontos4[1] = y+1.0;
				visitedTemp.add(pontos4);
			}

		if(x != 0)
			if (imagem.getRGB(x - 1, y) == -1){
				Double[] pontos5 = new Double[2];
				pontos5[0] = x-1.0;
				pontos5[1] = (double) y;
				visitedTemp.add(pontos5);
			}

		if(x != 0 && y != 0)
			if (imagem.getRGB(x - 1, y - 1) == -1){
				Double[] pontos6 = new Double[2];
				pontos6[0] = x-1.0;
				pontos6[1] = y-1.0;
				visitedTemp.add(pontos6);
			}

		if(y != 0)
			if (imagem.getRGB(x, y - 1) == -1){
				Double[] pontos7 = new Double[2];
				pontos7[0] = (double) x;
				pontos7[1] = y-1.0;
				visitedTemp.add(pontos7);
			}

		if(x < w -1 && y != 0)
			if (imagem.getRGB(x + 1, y - 1) == -1){
				Double[] pontos8 = new Double[2];
				pontos8[0] = x+1.0;
				pontos8[1] = y-1.0;
				visitedTemp.add(pontos8);
			}

		// if any visitor's point of the visitedTemp array is also present in the bifurc_points array, then that point
		// should be excluded from visitedTemp. For it is a redundancy, since the same point is present in two bifurcations.

		if (visitedTemp.size() > 2)
			for(int i =0; i < visitedTemp.size(); i++){
				Double [] i1 = visitedTemp.get(i);
				for(int j = 0; j < points_bifurc.size(); j++){
					Double [] i2 = points_bifurc.get(j);

					if(Arrays.equals(i1, i2)){
						visitedTemp.remove(i);
					}
				}
			}

		return visitedTemp;
	}


}
