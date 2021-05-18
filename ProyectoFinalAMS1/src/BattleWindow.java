import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;


public class BattleWindow extends JPanel{
	private JButton JCWarrior,JCWeapon,JRanking,botonFight,botonClearConsole;
	private JTextArea terminal;
	private WarriorPanel panelPlayer,panelCPU;
	//Panel principal de batalla
	public BattleWindow(String username,Warrior warriorPlayer,Weapon weaponPlayer,Warrior warriorCPU,Weapon weaponCPU) {
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		
		//Label
		JLabel text1 = new JLabel("Total Points:",JLabel.LEFT);
		JLabel tpoint = new JLabel("0");
		panel1.add(text1);
		panel1.add(tpoint);
		panel1.add(Box.createHorizontalGlue());
		//Botones norte
		this.JCWarrior = new JButton("Choose Warrior");
		JCWarrior.setOpaque(true);
		JCWarrior.setBackground(Color.white);
		this.JCWeapon = new JButton("Choose Weapon");
		JCWeapon.setOpaque(true);
		JCWeapon.setBackground(Color.white);
		this.JRanking = new JButton("Rankings");
		JRanking.setOpaque(true);
		JRanking.setBackground(Color.white);
		panel1.add(JCWarrior);
		panel1.add(JCWeapon);
		panel1.add(JRanking);
		panel1.setBackground(Color.black);
		
		add(panel1,BorderLayout.NORTH);
		//Jugador Humano
		this.panelPlayer = new WarriorPanel(Color.cyan,username,warriorPlayer,weaponPlayer);
		add(this.panelPlayer,BorderLayout.WEST);
		//Poner bien el versus CAMBIAR LO DE LA IMAGEN
		JPanel versusPanel = new JPanel();
		JLabel img = new JLabel();
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miicono = mipantalla.getImage("versus.png");
		
		Image dimg = miicono.getScaledInstance(180, 180,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		img.setIcon(imageIcon);
		img.setAlignmentX(CENTER_ALIGNMENT);
		versusPanel.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		versusPanel.setBackground(Color.black);
		versusPanel.add(img,BorderLayout.CENTER);
		add(versusPanel);
		//Jugador CPU
		panelCPU = new WarriorPanel(Color.red,"CPU",warriorCPU,weaponCPU);
		add(panelCPU,BorderLayout.EAST);
		
		//=======
		JPanel panel2 = new JPanel();
		JPanel panel21 = new JPanel();
		this.botonFight = new JButton("Fight");
		botonFight.setOpaque(true);
		botonFight.setBackground(Color.white);
		this.botonClearConsole = new JButton("Clear Console");
		botonClearConsole.setOpaque(true);
		botonClearConsole.setBackground(Color.white);
		panel21.add(botonFight);
		panel21.add(botonClearConsole);
		panel21.setBackground(Color.black);
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
	public WarriorPanel(Color colorUser,String namePlayer,Warrior warriorPlayer,Weapon weaponPlayer) {
		JPanel namePanel = new JPanel();
		JLabel namePlayer1 = new JLabel(namePlayer);
		namePlayer1.setFont(new Font("Arial",Font.BOLD,24));
		namePlayer1.setAlignmentX(CENTER_ALIGNMENT);
		namePanel.setOpaque(true);
		namePanel.setBackground(colorUser);
		namePanel.add(namePlayer1);
		add(namePanel);
		//Vida
		vida = new ProgressBar(150,150,Color.green,warriorPlayer.getHealth());
		add(vida);
		
		//ImageFOndo
		JPanelConFondo panelfondo = null;
		if (warriorPlayer.getRace_id() == 1) {
			panelfondo = new JPanelConFondo("dwrafFondo.png");
		}else if (warriorPlayer.getRace_id() == 2) {
			panelfondo = new JPanelConFondo("humanFondo.png");
		}else if (warriorPlayer.getRace_id() == 3) {
			panelfondo = new JPanelConFondo("elfFondo.png");
		}
		

		
		
		
		
		//Imagen
		JPanel panelImg = new JPanel();
		panelImg.setOpaque(false);
		JLabel img = new JLabel();
		String sCarpAct = System.getProperty("user.dir");
		Toolkit mipantalla = Toolkit.getDefaultToolkit();
		Image miicono = mipantalla.getImage(sCarpAct+File.separator+"imagenes"+File.separator+warriorPlayer.getImg());
		
		Image dimg = miicono.getScaledInstance(170, 290,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		img.setIcon(imageIcon);
		img.setAlignmentX(CENTER_ALIGNMENT);
		panelImg.add(img);
		panelImg.setToolTipText("<html><p>HP: " + warriorPlayer.getHealth()
        + "<br>Damage: " + warriorPlayer.getForce()
        + "<br>Defense: " + warriorPlayer.getDefense()
        + "<br>Agility: " + warriorPlayer.getAgility()
        + "<br>Speed: " + warriorPlayer.getVelocity()+"</p></hmtl>");
		
		panelfondo.add(panelImg);
		add(panelfondo);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//PanelStats
		//Imagen
		JPanel panelStats = new JPanel();
		JPanel panelGrid = new JPanel();
		JLabel img2 = new JLabel();
		String sCarpAct2 = System.getProperty("user.dir");
		Toolkit mipantalla2 = Toolkit.getDefaultToolkit();
		Image miicono2 = mipantalla2.getImage(sCarpAct+File.separator+"imagenes"+File.separator+weaponPlayer.getWeapon_url());
		
		Image dimg2 = miicono2.getScaledInstance(80, 80,
		        Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(dimg2);
		img2.setIcon(imageIcon2);
		panelStats.add(img2);
		img2.setToolTipText("<html><p>Speed: " + weaponPlayer.getPlus_velocity()
		+"<br>Damage: " + weaponPlayer.getPlus_force() + "</p></html>");
		//Progress Bar habilidades
		panelGrid.add(new JLabel("Power",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.red,warriorPlayer.getForce()+weaponPlayer.getPlus_force()));
		panelGrid.add(new JLabel("Agility",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.pink,warriorPlayer.getAgility()));
		panelGrid.add(new JLabel("Speed",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.yellow,warriorPlayer.getVelocity()+weaponPlayer.getPlus_velocity()));
		panelGrid.add(new JLabel("Defense",JLabel.RIGHT));
		panelGrid.add(new ProgressBar(0,0,Color.blue,warriorPlayer.getDefense()));
		
		panelGrid.setLayout(new GridLayout(4,2));
		panelGrid.setToolTipText("<html><p>Damage: " + (warriorPlayer.getForce() + weaponPlayer.getPlus_force())
		+ "<br> Defense: " + warriorPlayer.getDefense()
		+ "<br> Agility: " + warriorPlayer.getAgility()
		+ "<br> Speed: " + (warriorPlayer.getVelocity() + weaponPlayer.getPlus_velocity()) + "</p></html>");
		panelStats.setBackground(Color.gray);
		panelGrid.setBackground(Color.gray);
		panelStats.add(panelGrid);
		//Nombre del Personaje
		JPanel nameWarriorPanel = new JPanel();
		JLabel nameWarrior = new JLabel(warriorPlayer.getWarrior_name());
		nameWarrior.setFont(new Font("Arial",Font.BOLD,18));
		nameWarrior.setAlignmentX(CENTER_ALIGNMENT);
		nameWarriorPanel.add(nameWarrior);
		nameWarriorPanel.setBackground(Color.gray);
		nameWarriorPanel.setBorder(new EtchedBorder() );
		
		add(nameWarriorPanel);
		add(panelStats);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new LineBorder(colorUser));
	}
	
	public void setVida(int vide) {
		
		vida.setValue(vide);
		
		
	}
	public void setColor(Color color) {
		vida.setForeground(color);
	}
	
}
//Clase unica para cargar barras de progresso
class ProgressBar extends JProgressBar{
	
	public ProgressBar(int x,int y,Color color,int value) {
		
		if (x != 0) {
			setMinimum(0);
			setMaximum(value);
			 
			setValue(value);
			setForeground(color);
			setPreferredSize(new Dimension(x,y));
			setStringPainted(true);
		}else {
			
			setMinimum(0);
			setMaximum(12);
			 
			setValue(value);
			setForeground(color);
		}
		
		
	}
	
	
}
//Clase para poder poner con cosas en JtextArea por sysouts
class ConsoleJFrame extends OutputStream {
  private JTextArea textArea;

  public ConsoleJFrame( JTextArea text ) {
      textArea = text;
  }

  public void write(int b) throws IOException {
      textArea.append( String.valueOf((char)b) );
  }

}


//Clase para poner fotos de fondo
class JPanelConFondo extends JPanel {
	 
    private String imagen;
 
   public JPanelConFondo(String img) {
	   imagen = img;
   }
 
    @Override
    public void paint(Graphics g) {
    	String sCarpActf = System.getProperty("user.dir");
		Toolkit mipantallaf = Toolkit.getDefaultToolkit();
		Image miiconof = mipantallaf.getImage(sCarpActf+File.separator+"imagenes"+File.separator+imagen);
        g.drawImage(miiconof, 0, 0, getWidth(), getHeight(),
                        this);
 
        setOpaque(false);
        super.paint(g);
    }
 
    //...
}
