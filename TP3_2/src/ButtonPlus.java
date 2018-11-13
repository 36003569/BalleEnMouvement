import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JButton;
 
import javax.swing.JButton;
  
public class ButtonPlus extends JButton {
  private String name;
  public ButtonPlus(String str){
    super(str);
    this.name = str;
  }
        
  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D)g;
    GradientPaint gp = new GradientPaint(0, 0, Color.white, 0, 20, Color.black, true);
    g2d.setPaint(gp);
    g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    g2d.setColor(Color.white);
    g2d.drawString(this.name, this.getWidth() /2, (this.getHeight() / 2) + 5);
  }
  
  public void mouseClicked(MouseEvent event) { }
  
  public void setText(String str) {
	  super.setText(str);
	  this.name=str;
  }
}
