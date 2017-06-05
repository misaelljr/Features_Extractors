package extractor.metrics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * This class, the value of the image density is measured. To calculate the density is 
 * measured the number of pixels in the blood vessel by the number of pixels of the entire image.
 * 
 * @author Misael Jr
 *
 */

public class Density {

	/**
	 * // Function that measures the density of the image (amount of pixels of the blood vessel / amount of pixels of the whole image)
	 * @param imagem -- Object with image
	 * @param w -- Numbers of points on the x-axis
	 * @param h -- Numbers of points on the y-axis
	 * @return Density value
	 * @throws FileNotFoundException
	 */
	public void density_Extractor(BufferedImage image, int w, int h){

		double countVaso = 0;
		double countImage = 0;
		double totalPixelImg = 0;
		double dens = 0;

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {

				if(image.getRGB(i,j) == -1){
					countVaso++;
				}else{
					countImage++;
				}
			}
		}

		totalPixelImg = countImage + countVaso;
		dens = Math.atan2(countVaso, totalPixelImg);		

		System.out.println("###########Density Extractor###########");
		System.out.println("Density: " +dens);	
	}

}
