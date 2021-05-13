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
import java.io.File;
import java.io.PrintStream;

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
	private JButton JCWarrior,JCWeapon,JRanking,botonFight,botonClearConsole;
	private JTextArea terminal;
	private WarriorPanel panelPlayer,panelCPU;
	//Panel principal de batalla
	public BattleWindow(Warrior warriorPlayer,Weapon weaponPlayer,Warrior warriorCPU,Weapon weaponCPU) {
		
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		//Botones norte
		this.JCWarrior = new JButton("Choose Warrior");
		this.JCWeapon = new JButton("Choose Weapon");
		this.JRanking = new JButton("Rankings");
		panel1.add(JCWarrior);
		panel1.add(JCWeapon);
		panel1.add(JRanking);
		add(panel1,BorderLayout.NORTH);
		//Jugador Humano
		this.panelPlayer = new WarriorPanel(warriorPlayer,weaponPlayer);
		add(this.panelPlayer,BorderLayout.WEST);
		//Poner bien el versus CAMBIAR LO DE LA IMAGEN
		JLabel img = new JLabel();
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miicono = mipantalla.getImage("versus.jpg");

		Image dimg = miicono.getScaledInstance(100, 100,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		img.setIcon(imageIcon);
		img.setAlignmentX(CENTER_ALIGNMENT);
		add(img,BorderLayout.CENTER);
		//Jugador CPU
		panelCPU = new WarriorPanel(warriorCPU,weaponCPU);
		add(panelCPU,BorderLayout.EAST);
		
		//=======
		JPanel panel2 = new JPanel();
		JPanel panel21 = new JPanel();
		botonFight = new JButton("Fight");
		botonClearConsole = new JButton("Clear Console");
		panel21.add(botonFight);
		panel21.add(botonClearConsole);
		panel2.add(panel21);
		
		terminal = new JTextArea(8,100);
		
		JScrollPane jsp = new JScrollPane(terminal);
		PrintStream out = new PrintStream( new ConsoleJFrame( terminal ));
		System.setOut( out );
		panel2.add(jsp);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		add(panel2,BorderLayout.SOUTH);
		
	}
	public JButton getJCWarrior() {
		return JCWarrior;
	}
	
	public JButton getJCWeapon() {
		return JCWeapon;
	}
	
	public JButton getJRanking() {
		return JRanking;
	}
	public JButton getBotonFight() {
		return botonFight;
	}
	
	public JButton getBotonClearConsole() {
		return botonClearConsole;
	}
	public JTextArea getTerminal() {
		return terminal;
	}
	public void setTerminal(JTextArea terminal) {
		this.terminal = terminal;
	}
	public WarriorPanel getPanelPlayer() {
		return panelPlayer;
	}
	public void setPanelPlayer(WarriorPanel panelPlayer) {
		this.panelPlayer = panelPlayer;
	}
	public WarriorPanel getPanelCPU() {
		return panelCPU;
	}
	public void setPanelCPU(WarriorPanel panelCPU) {
		this.panelCPU = panelCPU;
	}
	
	
	
}
//Clase para cargar los paneles de los jugadores
class WarriorPanel extends JPanel{
	private ProgressBar vida;
	public WarriorPanel(Warrior warriorPlayer,Weapon weaponPlayer) {
		//Vida
		vida = new ProgressBar(150,150,Color.green,100);
		add(vida);
		
		//Image
		
		JLabel img = new JLabel();
		String sCarpAct = System.getProperty("user.dir");
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miicono = mipantalla.getImage(sCarpAct+File.separator+"imagenes"+File.separator+warriorPlayer.getImg());
		
		
		Image dimg = miicono.getScaledInstance(300, 350,
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
		String sCarpAct2 = System.getProperty("user.dir");
		Toolkit mipantalla2 = Toolkit.getDefaultToolkit();
		Image miicono2 = mipantalla2.getImage(sCarpAct+File.separator+"imagenes"+File.separator+weaponPlayer.getWeapon_url());

		Image dimg2 = miicono2.getScaledInstance(100, 100,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(dimg2);
		img2.setIcon(imageIcon2);
		panelStats.add(img2);
		
		panelGrid.add(new JLabel("Power",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.red,warriorPlayer.getForce()+weaponPlayer.getPlus_force()));
		panelGrid.add(new JLabel("Agility",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.pink,warriorPlayer.getAgility()));
		panelGrid.add(new JLabel("Speed",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.yellow,warriorPlayer.getVelocity()+weaponPlayer.getPlus_velocity()));
		panelGrid.add(new JLabel("Defense",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.blue,warriorPlayer.getDefense()));
		
		panelGrid.setLayout(new GridLayout(4,2));
		
		panelStats.add(panelGrid);
		
		add(panelStats);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	}
	
	public void setVida(int vide) {
		
		vida.setValue(vide);
		
		
	}
	
	
}
//Clase unica para cargar barras de progresso
class ProgressBar extends JProgressBar{
	
	public ProgressBar(int x,int y,Color color,int value) {
		
		
		 	
		if (x != 0) {
			setMinimum(0);
			setMaximum(100);
			 
			setValue(value);
			setForeground(color);
			setPreferredSize(new Dimension(x,y));
			setStringPainted(true);
		}else {
			
			setMinimum(0);
			setMaximum(100);
			 
			setValue(value*10);
			setForeground(color);
		}
		
		
	}
	
	
}
