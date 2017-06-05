package extractor.file_reader;

import java.io.PrintWriter;

import extractor.metrics.AVG_Size_Seg;
import extractor.metrics.Bifurcation;
import extractor.metrics.Density;
import extractor.metrics.EndPoints;
import extractor.nifti_reader.NiftiVolume;

public class File_Reader {

	String [] source;
	Bifurcation bifurc;
	Density densi;
	EndPoints endpoints;
	AVG_Size_Seg avg_size;
	
	/**
	 * Method for treating reading files as array
	 * @param source
	 * @throws Exception
	 */
	
	public void ReadFile_File(String[] source) throws Exception{
		this.source = source;
		bifurc = new Bifurcation();
		densi = new Density();
		endpoints = new EndPoints();
		avg_size = new AVG_Size_Seg();
		

		if (source.length == 0 || source.length > 2)
		{
			System.out.println("Usage: niftijio input.nii.gz [output]");
			System.out.println("Description: read a volume and optionally write it out again");
			return;
		}

		NiftiVolume volume = NiftiVolume.read(source[0]);

		int nx = (int) (volume.header.dim[1] * volume.header.pixdim[1]);
		int ny = (int) (volume.header.dim[2] * volume.header.pixdim[2]);
		int nz = (int) (volume.header.dim[3] * volume.header.pixdim[3]);
		int dim = volume.header.dim[4];
				
		if (dim == 0)
			dim = 1;

		if (source.length == 1)
		{
		    
			System.out.println("Image dimensions: " + nx + " " + ny + " " + nz);
			
			densi.extractorDensi(volume.data, nx, ny, nz, dim); // Density extractor
			endpoints.extratorCountBifurc(volume.data, nx, ny, nz, dim); // Endpoints extractor
			bifurc.extractorCountBifurc(volume.data, nx, ny, nz, dim); // Bifurcation extractor
			
		}

		else if (source[1].endsWith("txt"))
		{
			PrintWriter out = new PrintWriter(source[1]);

			out.println("volume ");
			out.println("dimensions:");
			out.println(nx + " " + ny + " " + nz + " " + dim);
			out.println("data:");
			
			for (int d = 0; d < dim; d++)
				for (int k = 0; k < nz; k++)
					for (int j = 0; j < ny; j++)
						for (int i = 0; i < nx; i++)
							
							out.println(volume.data[i][j][k][d]);

			out.println();
			out.close();
		}

		else
		{
			volume.write(source[1]);
		}


	}

}
