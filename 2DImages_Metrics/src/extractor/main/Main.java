package extractor.main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import extractor.metrics.Bifurcation;
import extractor.metrics.Density;
import extractor.metrics.Endpoints;

public class Main {

	public static void image_read(String file) throws IOException{

		File inputFile = new File(file); 
		BufferedImage image = ImageIO.read(inputFile);
		
		Density dens = new Density();
		Bifurcation bifurc = new Bifurcation();
		Endpoints endpoints = new Endpoints();

		int w = image.getWidth();
		int h = image.getHeight();

		dens.density_Extractor(image, w, h);
		endpoints.countEndPoints(image, w, h);
		bifurc.countBifurc(image, w, h);
		
	}	

	public static void main(String[] args) throws Exception {
		
		String dir = "/images_ref/";
		File source = new File(dir);
		File fList[] = source.listFiles(); 

		for ( int i = 0; i < fList.length; i++ ){ 
			
			File files = fList[i];			
			String file = dir + files.getName().toString();
			System.out.println("########"+ files.getName() + "########");
			image_read(file);
			System.out.println();
		
		}

	}
}