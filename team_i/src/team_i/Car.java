package team_i;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Car {
		public int x = 0;
		public int y = 0;
		public BufferedImage image;
		
		public Car(int x, int y, String imagePath){
			this.x = x;
			this.y = y;
			try {
				this.image =ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
}
