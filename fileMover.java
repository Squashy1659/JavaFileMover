import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;
import java.nio.file.*;

public class fileMover{

	private static void FileMover(){
		System.out.println("Script has been startet.");
		int i,a,g = 0;
		ArrayList<File> fileList = new ArrayList<File>();
		ArrayList<File> impoList = new ArrayList<File>();
		String fileLocation, filename = null;
		BufferedImage img = null;
		
		try{
			// Loading all file locations into memory---------------------------------------------------
			BufferedReader reader = new BufferedReader(new FileReader("fileLocations.txt"));
			FileWriter impo = new FileWriter("ImpossibleToCopyFiles.txt");
			Scanner sc = new Scanner (reader);
			i = 0;
			a = 0;
			g = 0;

			while(sc.hasNextLine()){
				i++;
				fileLocation = sc.nextLine();
				fileList.add(new File(fileLocation));
				
				System.out.println("Found " + i + " paths in the file.");
			}
			sc.close();
			System.out.println("Succesfully loaded all paths into memory");
						
			while(g<i){
				try{
				img = ImageIO.read(fileList.get(g));
				filename = "./javaFileTest/" + Integer.toString(g) + ".png";
				ImageIO.write(img, "png", new File(filename));
				System.out.println("Written file number " + g + " to disk.");
				g++;
				}
				catch(Exception e){
					System.out.println("Coulnd't write image " + g + " to disk or it was not located.");
					g++;
					impoList.add(fileList.get(g));
				}
			}
			System.out.println("Impossible to copy files:");
			writeImpoFiles(impoList);
			fileList.clear();
		}
		catch(Exception e){
			System.out.println("An error has occured! Maybe check the location of your files...");
		}
	}


	private static void writeImpoFiles(ArrayList impo){
		int r = 0;
		int imlength = impo.size();

		while(r<imlength){
			System.out.println(impo.get(r));
			r++;
		}
	}

	public static void main(String[] args){
		FileMover();
	}
}
