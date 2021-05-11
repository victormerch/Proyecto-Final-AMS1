import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class BattleWindow extends JPanel{
	
	public BattleWindow() {
		
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.add(new JButton("Choose Character"));
		panel1.add(new JButton("Choose Weapon"));
		panel1.add(new JButton("Ranking"));
		add(panel1,BorderLayout.NORTH);
		
		add(new WarriorPanel("humano.png"),BorderLayout.EAST);
		//Poner bien el versus
		JLabel img = new JLabel();
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miicono = mipantalla.getImage("versus.jpg");

		Image dimg = miicono.getScaledInstance(100, 100,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		img.setIcon(imageIcon);
		img.setAlignmentX(CENTER_ALIGNMENT);
		add(img,BorderLayout.CENTER);
		add(new WarriorPanel("enano.png"),BorderLayout.WEST);
		
		
		JPanel panel2 = new JPanel();
		JPanel panel21 = new JPanel();
		panel21.add(new JButton("Fight"));
		panel21.add(new JButton("Clear Console"));
		panel2.add(panel21);
		JTextArea terminal = new JTextArea(8,100);
		JScrollPane jsp = new JScrollPane(terminal);
		panel2.add(jsp);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		add(panel2,BorderLayout.SOUTH);
		
		
		
		
		
	}
}
class WarriorPanel extends JPanel{
	public WarriorPanel(String imgURL) {
		add(new ProgressBar(50,30,Color.green));
		
		//Image
		JLabel img = new JLabel();
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miicono = mipantalla.getImage(imgURL);

		Image dimg = miicono.getScaledInstance(200, 300,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		img.setIcon(imageIcon);
		img.setAlignmentX(CENTER_ALIGNMENT);
		add(img);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//PanelStats
		JPanel panelStats = new JPanel();
		JPanel panelGrid = new JPanel();
		JLabel img2 = new JLabel();
		Toolkit mipantalla2 = Toolkit.getDefaultToolkit();
		Image miicono2 = mipantalla2.getImage("arco.png");

		Image dimg2 = miicono2.getScaledInstance(80, 80,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(dimg2);
		img2.setIcon(imageIcon2);
		panelStats.add(img2);
		
		panelGrid.add(new JLabel("Power",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.red));
		panelGrid.add(new JLabel("Agility",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.pink));
		panelGrid.add(new JLabel("Speed",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.yellow));
		panelGrid.add(new JLabel("Defense",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.blue));
		
		panelGrid.setLayout(new GridLayout(4,2));
		
		panelStats.add(panelGrid);
		
		add(panelStats);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	}

	
}
class ProgressBar extends JProgressBar{
	
	public ProgressBar(int x,int y,Color color) {
		
		setMinimum(0);
		setMaximum(100);
		 
		setValue(20);
		setForeground(color);
		 	
		if (x != 0) {
			setPreferredSize(new Dimension(x,y));
			setStringPainted(true);
		}
		
	}

	
	
}
