package gameBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.UUID;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import gameMechanics.Biotic;

public class Board extends JPanel {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


	public static int DimentionX = 1000;
	public static int DimentionY = 1000;


	public static void setScreenSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		DimentionX = (int) screenSize.getWidth();
		DimentionY = (int) screenSize.getHeight();

	}

	public static void addEscapeListener(final JFrame dialog) {
		ActionListener escListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		};

		dialog.getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

	}
	@Override
	public void paint(Graphics g) throws NullPointerException {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int red = 0;
		int black = 0;

		for(int x = 0; x < Biotic.getNum_BIOTICS_INGAME(); x++) {
			Biotic bio = Biotic.getBIOTICS_INGAME().get(x);
			for (int y = 0; y < bio.getNetwork().size();y++) {
				if(bio.getNetwork().get(y).getNetwork().contains(bio)) {
					int[] cords1 = bio.getNetwork().get(y).getCurrentCords();
					int[] cords2 = bio.getCurrentCords();

					g2d.setColor(Color.MAGENTA);
					g2d.drawLine(cords1[0]*10+5, cords1[1]*10+5, cords2[0]*10+5, cords2[1]*10+5);
				}
			}

			//Colors
			g2d.setColor(bio.getColor());

			//Counter
			if (bio.getColor() == Color.red) {
				red++;
			}
			else {
				black++;
			}
			g2d.fillRect(bio.getCurrentCords()[0] * 10, bio.getCurrentCords()[1] * 10, 10, 10);
		}

		g2d.setColor(Color.BLACK);
		g2d.drawString("RED: " + red, 50, 50);
		g2d.drawString("BLACK: " + black, 50, 100);
	}



	static public int getDimentionX() {
		return DimentionX/10-1;
	}
	static public int getDimentionY() {
		return DimentionY/10-1;
	}






}
