package gameBoard;
import java.awt.EventQueue;
import javax.swing.JFrame;


public class Application extends JFrame {
	
	public void RunApp() {
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		
        setSize(2500, 200);

        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
	    
	    EventQueue.invokeLater(() -> {
	        Application app = new Application();
	        app.setVisible(true);
	    });
	}

}
