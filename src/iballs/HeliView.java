package iballs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.BitSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class HeliView extends JFrame {

	private JPanel contentPane;

	Image heli;
	public HeliView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1270, 990);
		
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				
		ImageIcon icon = new ImageIcon("images/heli.jpg");
		icon.setImage(icon.getImage().getScaledInstance(1260, 980, Image.SCALE_SMOOTH));
		JLabel jLabel = new JLabel(icon);		
		contentPane.add(jLabel, BorderLayout.CENTER);
	}
	

}
