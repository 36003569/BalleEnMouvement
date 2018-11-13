import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Fenetre extends JFrame {
  private GamePanel pan = new GamePanel();
  private JPanel container = new JPanel();
  private JButton add = new JButton("add");
  private JButton remove = new JButton("remove");
  private JButton startstop= new JButton("start");
  JPanel south = new JPanel();

  public Fenetre(){
    this.setTitle("Animation");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);
    south.add(add);
    south.add(startstop);
    south.add(remove);
    container.add(south, BorderLayout.SOUTH);

    this.setContentPane(container);
    this.pack();
    this.setVisible(true);
    this.setResizable(false);
    
    add.addActionListener(new BoutonListener2());
    startstop.addActionListener(new BoutonListener());
    remove.addActionListener(new BoutonListener3());
  }
  //Le reste ne change pas
  
  class BoutonListener implements ActionListener{
	    //Redéfinition de la méthode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
	    	if(GamePanel.running == true) {
	    		GamePanel.running = false;
	    		startstop.setText("stop");
	    		pan.t.state=false;
	    	}
	    	else {
	    		GamePanel.running = true;
	    		startstop.setText("start");
	    		pan.t.state=true;
	    	}
	    }
	  }
  
  class BoutonListener2 implements ActionListener{ //ajouter un timer
	    //Redéfinition de la méthode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) {
	    	GamePanel.balls.add(new Ball());
	    	if (GamePanel.running == false){
	    		for(int i = 0; i < GamePanel.balls.size(); i++) {
	    			pan.gameRender();
	    			pan.gameDraw();
	    		}
	    	}
	    }
	  }
  
  class BoutonListener3 implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
	    	if (GamePanel.balls.size()!=0) {
	    		GamePanel.balls.remove(0);
	    		if (GamePanel.running == false){
	    			pan.gameRender();
	    			pan.gameDraw();
	    		}
	    	}
	    }
	}
}