import java.awt.*;
public class Ball {

	//FIELDS
	private double x;
	private double y;
	private int r;
	
	private double dx;
	private double dy;
	private double rad;
	private double speed;
	
	private Color color1;
	float red = (float)Math.random();
	float blue = (float)Math.random();
	float green = (float)Math.random();
	
	//CONSTRUCTOR
	public Ball() {
		
		color1 = new Color(red,green,blue);//aléatoire;
		speed = 2;//aléatoire;
		r = 5;
		x = Math.random() * GamePanel.WIDTH ;
		y = Math.random() * GamePanel.HEIGHT ;
		double angle = Math.random()* 140+20;
		rad = Math.toRadians(angle);
		
		dx = Math.cos(rad) * (speed);
		dy = Math.sin(rad) * (speed);
	}
	
	//FUNCTIONS
	public double getx() {return x;}
	public double gety() {return y;}
	public double getr() {return r;}
	
	public void update() {
		x += dx;
		y += dy;
		
		// inutile de prendre en compte le rebond des balles que si ils sont à l'intérieur du panneau car ils apparaissent dedans directement.
		/*if(!ready) {
			if(x>r && x<GamePanel.WIDTH - r &&
					y > r && y < GamePanel.HEIGHT - r) {
				ready = true;
			}
		}*/
		
		if(x < r && dx < 0) dx = -dx;
		if(y < r && dy < 0) dy = -dy;
		if(x > GamePanel.WIDTH -r && dx > 0) dx = -dx;
		if(y > GamePanel.HEIGHT -r && dy > 0) dy = -dy;
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(color1);
		g.fillOval((int) (x-r), (int)(y-r), 2*r, 2*r);
		
		// grossir les lignes du cercle.
		g.setStroke(new BasicStroke(3));
		// fonce les couleurs du cercle par rapport à la couleur de la balle
		g.setColor(color1.darker());
		g.drawOval((int) (x-r), (int)(y-r), 2*r , 2*r);
		g.setStroke(new BasicStroke(1));
	}
	
}
