import java.util.Timer;

public class Tiimer implements Runnable {
	
	public int second;
	public boolean state;
	
	public Tiimer() {
		super();
		this.state = true;
		this.second = 0;
	}
	public void run() {
		while(true) {
			while(state == true) {
				try {
					this.second++;
					Thread.sleep(1000);
					System.out.println(""+this.second);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				while(state == false) {
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
		}
	}

}
