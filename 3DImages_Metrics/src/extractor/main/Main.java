package extractor.main;
import java.io.File;

import extractor.file_reader.File_Reader;

public class Main {

	public static void main(String[] args) throws Exception {

		String dir = "/images_ref/";
		File directory = new File(dir);
		File fList[] = directory.listFiles(); 

		for ( int i = 0; i < fList.length; i++ ){ 

			File files = fList[i];
			File_Reader input = new File_Reader();			

			String [] file = {"/images_ref/"+files.getName().toString()};
			System.out.println("########"+ files.getName() + "########");
			input.ReadFile_File(file);
			System.out.println();

		}

	}
}