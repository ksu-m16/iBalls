package iballs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel() {
			public void paint(Graphics g) {				
				redraw(g);
			};
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				
		addWindowListener(new WindowListener() {						
			@Override
			public void windowClosed(WindowEvent arg0) {}
			@Override
			public void windowActivated(WindowEvent e) {}
			@Override
			public void windowClosing(WindowEvent e) {				
				HeliView hv = new HeliView();
				hv.setVisible(true);				
			}			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			@Override
			public void windowDeiconified(WindowEvent e) {}
			@Override
			public void windowIconified(WindowEvent e) {}
			@Override
			public void windowOpened(WindowEvent e) {}
		});
	}	
	
	private List<Integer> solutions = new LinkedList<Integer>();	
	public void setSolutions(List<Integer> sols) {
		solutions = new LinkedList<Integer>(sols);
	}
	
	public void drawSolution(Graphics g, int h, int ox, int oy, int sol) {		
		int s = h / 20; //space between balls
		int d = (h - 4*s) / 5; //ball diameter
		int d2 = h / 5 + 2*s;
		
		Color vc = new Color(0xFF00FF);
		Color vca = new Color(0x20FF00FF, true);
		Color gc = new Color(0x00FF00);
		Color gca = new Color(0x2000FF00, true);
		Color bc = new Color(0x000000);
		Color bca = new Color(0x20000000, true);				
		
		Color f = vc;
		Color fa = vca;
		
		for (int row = 0; row < 5; ++row) {
			for (int col = 0; col <= row; ++col) {
				int x = ox + h/2 + col*(d + s) - row * (d + s) / 2;
				int y = oy + d/2 + row*(d + s);
								
				if ((sol & 1) != 0) {
					f = vc;
					fa = vca;
				} else {
					f = gc;
					fa = gca;
				}
				
				if ((row == 2) && (col == 1)) {
					f = bc;
					fa = bca;
				}

				g.setColor(f);
				g.fillOval(x - d/2, y - d/2, d, d);
				g.setColor(fa);
				g.fillOval(x - d2/2, y - d2/2, d2, d2);				
				sol >>= 1;
			}			
		}				
	}
	
	public void redraw(Graphics g) {
		int n = solutions.size();
		int wx = contentPane.getWidth();
		int wy = contentPane.getHeight();				
				
		int h = (int)Math.sqrt((double)(wx*wy)/(double)n);

		int nx = wx / h;
		int ny = (int)Math.ceil((double)n / (double)nx);		
		h = wy / ny;
		
		int ox = (wx - nx*h) / 2;
		int oy = (wy - ny*h) / 2;
		
		int x = 0;
		int y = 0;
		int s = h/5;
		for (Integer sol : solutions) {			
			drawSolution(g, h - s, ox + x*h, oy + y*h, sol);			
			x++;
			if (x >= nx) {
				x = 0;
				y++;
			}
		}
	}		
}
