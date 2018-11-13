import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{

	//FIELDS
	public static int WIDTH = 1000;
	public static int HEIGHT = 800;
	
	private Thread thread;
	public static boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	public static ArrayList<Ball> balls;
	
	public static int score;
	//timer
		Tiimer t = new Tiimer();
		Thread timer1 = new Thread(t);
		
	//CONSTRUCTOR
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		score = 0;
	}
	
	//FUNCTIONS
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run() {
		running=true;
		//lancer chrono
		timer1.start();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		balls = new ArrayList<Ball>();
		// balls number instance
		for(int i = 0; i <30; i++) {
			balls.add(new Ball());
		}
		
		//GAME LOOP
		while(running) {
			gameUpdate();
			gameRender();
			gameDraw();
			//System.out.println(running);
			try {
				Thread.sleep(3);
			}catch(InterruptedException e) {
		        e.printStackTrace();
			}
			
			//sleep my thread
			while(!running) {
				try {   // thread bug processeur boucle trop vite, pas vraiment de pause
					System.out.println(running);
					Thread.sleep(1000);
					System.out.println(balls.size());
					//add.addActionListener(new BoutonListener2());
					//moins.addActionListener(new BoutonListener3());
				} catch (InterruptedException e) {
					e.printStackTrace();
		      }
			}
		}
		
	}
	
	private void gameUpdate() {
		
		// ball update
		for(int i = 0; i < balls.size(); i++) {
			balls.get(i).update();
		}
		
		//ball collision
		for(int i = 0; i< balls.size(); i++) {
			Ball a = balls.get(i);
			double ax = a.getx();
			double ay = a.gety();
			double ar = a.getr();
			
			for(int j = 0; j< balls.size(); j++) {
				Ball b = balls.get(j);
				double bx = b.getx();
				double by = b.gety();
				double br = b.getr();
				
				double dx = bx - ax;
				double dy = by - ay;
				double dist = Math.sqrt(dx * dx + dy * dy);
				
				if(dist < br + ar && i!=j) { //arranger ce probleme;
					//System.out.println("j : "+ j +"& i : "+i);
					balls.remove(j);
					balls.remove(i);
					//System.out.println(balls.size());
					score++; //augmentation du score
					break;
				}
			}
		}
	}
	
	public void gameRender() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//score
		g.setColor(Color.BLACK);
		g.drawString("Score : "+score, 50, 50);
		//dessiner chrono
		g.drawString("temps : "+t.second + " sec", 850, 50);
		//draw balls
		for(int i = 0; i < balls.size(); i++) {
			balls.get(i).draw(g);
		}
	}
	
	public void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
}
