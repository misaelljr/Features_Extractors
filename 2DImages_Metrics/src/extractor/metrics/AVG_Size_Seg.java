package extractor.metrics;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class the average of segments of the image is measured. The segment extractor uses the bifurcation and termination points as references. 
 * Thus, a segment of the image is classified as a bifurcation point to a terminal point or a point of bifurcation to another point of bifurcation.
 * 
 * @author Misael Jr
 *
 */

public class AVG_Size_Seg {

	ArrayList<Double[]> points_bifurc = new ArrayList<Double[]>();
	ArrayList<Double[]> points_endpoints = new ArrayList<Double[]>();
	ArrayList<Double[]> A = new ArrayList<Double[]>();
	double nrSeg=0;
	double size_seg = 0; 


	/**
	 * Function that measures the amount of segments in an image according to the points of bifurcation and identified finals.
	 * 
	 * @param image -- Matrix with image points.
	 * @param bifurc_points -- bifurcations points
	 * @param end_points -- end points
	 * @param w -- Numbers of points on the x-axis
	 * @param h -- Numbers of points on the y-axis
	 * @return Amount segments
	 * @throws FileNotFoundException
	 */

	public void extratorAVGSeg(BufferedImage image, ArrayList<Double[]> points_bifurc, ArrayList<Double[]> points_endpoints,
			int w, int h) throws FileNotFoundException{

		this.points_bifurc = points_bifurc;
		this.points_endpoints = points_endpoints;	
		double avg = 0;

		// Add bifurcation points and endpoints to an array and then sort them.
		A.addAll(points_endpoints);
		A.addAll(points_bifurc);
		sortArray(A);

		while(A.size() > 1){

			Double[] ponto1 = A.get(0);
			ArrayList<Double[]> ways;
			ArrayList<Double[]> pontoTemp = null;
			ways = new ArrayList<Double[]>();	

			int x = ponto1[0].intValue();
			int y = ponto1[1].intValue();
			A.remove(0); //Extract points from the first coordinate and delete from the array

			// traverse all points between the first and second points of A, classifying as segment
			for (int i = x; i < w; i++){
				for (int j = y; j < h; j++){

					if (image.getRGB(x, y) == -1){
						pontoTemp = new ArrayList<Double[]>();
						Double[] pontos = {(double) i, (double) j};
						pontoTemp.add(pontos);

						if(check(pontoTemp, A)){ //check if the point is present in A. That is, if it is a terminal or bifurcation.
							ways.add(pontos);
						}else{
							continue;
						}
					}else
						continue;
				}
			}

			nrSeg++;
			size_seg += ways.size();

		}

		//Average Segment Size
		avg = (size_seg/nrSeg);

		//double avg_normalized = getNormalized(avg);

		System.out.println("###########AVG Size Segments Extractor#########");
		System.out.println("AVG Size Segments: " +avg);	

	}

	/**
	 * Function that checks whether a point is already present in another array
	 * @param array1 -- Array 1
	 * @param array2 - -Array 2
	 * @return If the point is present, returns false. Otherwise, it returns true.
	 */
	public boolean check(ArrayList<Double[]> array1, ArrayList<Double[]> array2){

		for(int i = 0; i < array1.size();i++){
			Double[] i1 = array1.get(i);
			for(int j = 0; j < array2.size();j++){
				Double[] i2 = array2.get(j);

				if(Arrays.equals(i1, i2)){
					return false;
				}

			}
		}

		return true;

	}

	/**
	 * Function that sorts the points of an array of type ArrayList <Double []>
	 * @param array -- Array to be sorted
	 */
	public void sortArray(ArrayList<Double[]> array){

		Collections.sort(array,new Comparator<Double[]>() {
			@Override
			public int compare(Double[] strings, Double[] otherStrings) {
				return strings[0].compareTo(otherStrings[0]);
			}
		});

	}

}
