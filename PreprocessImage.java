import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PreprocessImage {

	String[][] result;
	
	public static void main(String[] args){
		new PreprocessImage();
	}
	public PreprocessImage()
	{
		int neighbourhood = 3;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/home/java/ankit/srep/Analytics/barrackObama.jpg"));

			int width = img.getWidth();
			int height = img.getHeight();
			int argbComponents = 4;
			result = new String[width * height][neighbourhood*neighbourhood*4+2];
			for(int i=0;i<width;i++){
				//if(i % 10 == 0) System.out.println(i);
				for(int j=0;j<height;j++){
					result[i*height+j][(neighbourhood*neighbourhood*argbComponents)]=""+i;
					result[i*height+j][(neighbourhood*neighbourhood*argbComponents)+1]=""+j;
					for(int n1=0;n1<neighbourhood;n1++){
						for(int n2=0;n2<neighbourhood;n2++){
							int n1Adj = i + n1 - (neighbourhood-1)/2;
							int n2Adj = j + n2 - (neighbourhood-1)/2;
							if(n1Adj >0 && n1Adj <width && n2Adj >0 && n2Adj <height)
							{
								int p = img.getRGB(n1Adj, n2Adj);

							    //get alpha
							    int a = (p>>24) & 0xff;
							    //get red
							    int r = (p>>16) & 0xff;
							    //get green
							    int g = (p>>8) & 0xff;
							    //get blue
							    int b = p & 0xff;
							    //System.out.println(""+a+" "+r+" "+g+" "+b);
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)] = ""+a;
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)+1] = ""+r;
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)+2] = ""+g;
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)+3] = ""+b;
							}
							else
							{
								result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)] = "NA";
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)+1] = "NA";
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)+2] = "NA";
							    result[i*height + j][(n1*neighbourhood*argbComponents)+(n2*argbComponents)+3] = "NA";
							}
						}  
					}
				}  
			}
			BufferedWriter br = new BufferedWriter(new FileWriter("/home/java/ankit/srep/Analytics/preprocessedImage.csv"));
			StringBuilder sb = new StringBuilder();
			for (int i=0;i<result.length;i++) {
				for (int j=0;j<result[i].length;j++) {
					if(j!=0)sb.append(",");
					sb.append(result[i][j]);
				}
				sb.append(System.getProperty("line.separator"));
			}

			br.write(sb.toString());
			br.close();
		} catch (IOException e) {
		}
		
		

	}
}
