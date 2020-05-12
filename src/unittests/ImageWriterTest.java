/**
 * 
 */
package unittests;

import static org.junit.Assert.*;


import org.junit.Test;

import primitives.*;
import renderer.*;

/**
 * @author rinat
 *
 */
public class ImageWriterTest {

	@Test
	public void test() {
		ImageWriter picture=new ImageWriter("grid_picture",1600,1000,800,500);
		for(int i=0;i<800;i++)
		{
			for(int j=0;j<500;j++)
			{
				if(i%50==0 || j%50==0)
				{
					picture.writePixel(i, j, (new Color(250,250,250)).getColor());
				}
				else
				{
					picture.writePixel(i, j, (new Color(125,0,125)).getColor());
				}
			}
		}
		picture.writeToImage();
		
	}

}
