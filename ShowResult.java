import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ShowResult {
	public ShowResult(){
		
		String csvFile = "/home/java/ankit/srep/Analytics/result.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        ArrayList<String> data = new ArrayList<String>(); 
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] imageData = line.split(cvsSplitBy);
                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
                data.add(line);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //int width = ((String)(data.get(0))).split(",").length;
        
        int maxX = 0; int maxY = 0;
        for(int i=1;i<data.size()-1;i++)
        {
        	String[] lineSplit = new String[4];
        	lineSplit = ((String)(data.get(i))).split(",");
        	//System.out.println(data.get(i));
        	String xValue = lineSplit[1].trim();
        	//xValue = xValue.substring(1, xValue.length()-1);
        	if(maxX < Integer.parseInt(xValue)) maxX = Integer.parseInt(xValue);
        	
        	String yValue = lineSplit[2].trim();
        	//yValue = yValue.substring(1, yValue.length()-1);
        	if(maxY < Integer.parseInt(yValue)) maxY = Integer.parseInt(yValue);
        }
        System.out.println(""+maxX+" "+maxY);
//        int
		BufferedImage image = new BufferedImage(maxX+1, maxY+1, BufferedImage.TYPE_INT_RGB);
		
		for(int i=1;i<data.size()-1;i++)
        {
        	String[] lineSplit = new String[4];
        	lineSplit = ((String)(data.get(i))).split(",");
        	//System.out.println(data.get(i));
        	String xValue = lineSplit[1].trim();
        	int xVal = Integer.parseInt(xValue);
        	//xValue = xValue.substring(1, xValue.length()-1);
        	//if(maxX < xVal) maxX = Integer.parseInt(xValue);
        	
        	String yValue = lineSplit[2].trim();
        	int yVal = Integer.parseInt(yValue);
        	//yValue = yValue.substring(1, yValue.length()-1);
        	//if(maxY < yVal) maxY = Integer.parseInt(yValue);
        	
        	String cluster = lineSplit[3].trim();
        	if(cluster.equals("1"))
        		image.setRGB(xVal, yVal, (0 << 16) | (0 << 8) | 200);
        	else if(cluster.equals("2"))
        		image.setRGB(xVal, yVal, (200 << 16) | (200 << 8) | 0);
        	else if(cluster.equals("3"))
        		image.setRGB(xVal, yVal, (0 << 16) | (200 << 8) | 0);
        }
		
		File outputFile = new File("/home/java/ankit/srep/Analytics/barrackObama.png");
		try {
			ImageIO.write(image, "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new ShowResult();
	}
}
