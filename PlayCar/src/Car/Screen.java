package Car;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends Canvas implements KeyListener {

   private int x = 0;
   private int y = 50;
   
   private int c = 0;
   private int d = 0;
   
   private BufferedImage image;
   private static final long serialVersionUID = 1L;
   
   public Screen() {
      try {
         image = ImageIO.read(new File("/C:/Users/win/eclipse-workspace/PlayCar/src/Car/car.png"));
         
         addKeyListener(this);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   @Override
   public void paint(Graphics g) {
      // TODO Auto-generated method stub
      super.paint(g);
      g.drawImage(image, x, y, this);
      g.drawImage(image, c, d, this);
   }
   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      int gap = 10;
      switch(e.getKeyCode())
      {
      case KeyEvent.VK_UP:
         y -= gap;
         d -= gap;
         break;
      case KeyEvent.VK_DOWN:
         y += gap;
         d += gap;
         break;
      case KeyEvent.VK_LEFT:
         x -= gap;
         c -= gap;
         break;
      case KeyEvent.VK_RIGHT:
         x += gap;
         c += gap;
         break;
      }
      
      System.out.println(x+", "+y);
      System.out.println(c+", "+d);
      repaint();
   }

}