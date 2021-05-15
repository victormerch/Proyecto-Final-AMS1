import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame{
	private static Scanner input = new Scanner(System.in);
	//Boolean for fight
	private static boolean inFight = false;
	private static boolean fightBool = true;
	private static boolean fightfinal = false;
	//Panels
	private static InitialWindow panel1;
	private static ChooseWarrior_Window panel2;
	private static ChooseWeapon_Panel panel3;
	private static BattleWindow panel4;
	
	//Jugadores
	private static Warrior warriorPlayer;
	private static Weapon weaponPlayer;
	private static Warrior warriorCPU;
	private static Weapon weaponCPU;
	
	//ConnexionDB and username
	private static ConnectionDB conn;
	
	private static boolean cpu_player_bool;
	
	private int contRounds = 0;
	private int contTourn = 0;
	
	private Player player1 = null, player2 = null;
	//==Instanciamiento classe
	public static void main(String[] args) {
		new Main();
	
	}
	public Main() {
		//Cargamos el Frame inicial
		this.setTitle("Welcome");
		setSize(400, 450);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//First Panel
		panel1 = new InitialWindow();
		JButton boton1 = panel1.getButton();
		
		boton1.addActionListener(new activeBotons());
		add(panel1);
		
		
		setVisible(true);
	}
	//Battle Algorithm
	public void algoritmBattle(Player user,Player bot) {
		
		
		if (fightBool) {
			inFight = true;
			if (user.getVel() > bot.getVel()) {
				player1 = user;
				player2 = bot;
			}

			else if (bot.getVel() > user.getVel()) {
				player1 = bot;
				player2 = user;

			}

			else if (bot.getVel() == user.getVel()) {
				if (user.getAgil() > bot.getAgil()) {
					player1 = user;
					player2 = bot;

				}

				else if (bot.getAgil() > user.getAgil()) {
					player1 = bot;
					player2 = user;

				}

				else if (bot.getAgil() == user.getAgil()) {
					int randomAt = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);
					if (randomAt  == 1) {
					player1 = user;
					player2 = bot;
				}
					if (randomAt  == 2) {
						player1 = bot;
						player2 = user;
					}	
				}
			}
			System.out.println("Empieza " + player1.getName());
			fightBool = false;
		}
		
		//RONDAS
		if(bot.getLife() > 0 && user.getLife() > 0) {
			
			contRounds++;
			contTourn++;
			
			System.out.println("~~ Ronda: " + contRounds + " ~~");
			//System.out.println(player1.getName() + " hace " + player1.getAtack());
			player2.setLife(player2.getLife() - player1.getAtack());
			System.out.println(player2.getName() + " tiene " + player2.getLife());
			//Color vida CPU
			panel4.getPanelCPU().setVida(player2.getLife());
			if (player2.getLife()<=(player2.getTotalHP()*0.5) && player2.getLife()>(player2.getTotalHP()*0.25)) {
				panel4.getPanelCPU().setColor(Color.yellow);
			}
			if (player2.getLife()<=(player2.getTotalHP()*0.25)) {
				panel4.getPanelCPU().setColor(Color.red);
			}
			//
			if (player2.getLife() > 0) {
				//System.out.println(player2.getName() + " hace " + player2.getAtack());
				player1.setLife(player1.getLife() - player2.getAtack());
				//
				System.out.println(player1.getName() + " tiene " + player1.getLife());
				
				//Color vida Jugador
				panel4.getPanelPlayer().setVida(player1.getLife());
				if (player1.getLife()<=(player1.getTotalHP()*0.50) && player1.getLife()>(player1.getTotalHP()*0.25)) {
					panel4.getPanelPlayer().setColor(Color.yellow);
				}
				if (player1.getLife()<=(player1.getTotalHP()*0.25))  {
					panel4.getPanelPlayer().setColor(Color.red);
				}
			}
			if (player2.getLife() < 1) {
				System.out.println(player2.getName() + " ha sido derrotado");
				fightfinal = true;
			}
			if (player1.getLife() < 1) {
				System.out.println(player1.getName() + " ha sido derrotado");
				fightfinal = true;
			}
			
			
			
		}
		//ACABA LA PARTIDA
		if(fightfinal) {
			//===Cuando gana algun la partida de hace lo siguiente
			//Cuando pierdes:
			if (bot.getLife()>user.getLife()) {
				int confirmado = JOptionPane.showConfirmDialog(null, "Has Perdido ¿Quieres volver a jugar con un personaje diferente?");

				if (JOptionPane.OK_OPTION == confirmado) {
				  
				   remove(panel4);
				   setTitle("Choose Warrior");
				   setSize( 900, 600);
				   setLocationRelativeTo(null);
				   setDefaultCloseOperation(EXIT_ON_CLOSE);
				   panel2 = new ChooseWarrior_Window(conn.getWarriorContainer());
				   for (JButton boton : panel2.getButtonArray()) {
					   boton.addActionListener(new activeBotons());
				   }
				   add(panel2);
				}
				else {
					dispose();
				}
				   
				
			}
			//Cuando ganas:
			else {
				int confirmado = JOptionPane.showConfirmDialog(null, "Has Ganado ¿Quieres volver a jugar?");

				if (JOptionPane.OK_OPTION == confirmado) {

					ArrayList<Warrior> warriors2 = conn.getWarriorContainer();
					Main.warriorCPU = warriors2.get((int) Math.floor(Math.random()*(warriors2.size()-2+1)+1));
					ArrayList<Weapon> weapons2 = conn.getWeaponContainer(warriorCPU);
					Main.weaponCPU = weapons2.get((int) Math.floor(Math.random()*(weapons2.size()-2+1)+1));
					Main.cpu_player_bool = true;
					
					
					remove(panel4);
					
					setTitle("Battle");
					setSize(940, 795);
					setLocationRelativeTo(null);
					panel4 = new BattleWindow(panel1.getUsername().getText(),Main.warriorPlayer, Main.weaponPlayer,Main.warriorCPU, Main.weaponCPU);
					JButton JCWarrior = panel4.getJCWarrior();
					JButton JCWeapon = panel4.getJCWeapon();
					JButton  botonFight= panel4.getBotonFight();
					JButton botonClearConsole = panel4.getBotonClearConsole();
					JCWarrior.addActionListener(new activeBotons());
					JCWeapon.addActionListener(new activeBotons());
					botonFight.addActionListener(new activeBotons());
					botonClearConsole.addActionListener(new activeBotons());
					add(panel4);
				}
				   
				else {
					dispose();
				}
				  
			}
			contRounds = 0;
			contTourn = 0;
			fightBool = true;
			fightfinal = false;
			inFight = false;
		}
		
		
	}
	//Events
	class activeBotons implements ActionListener{
		
		
		public void actionPerformed(ActionEvent e) {
			//Coger personajes
			if ((e.getActionCommand().equals("Play") ||e.getActionCommand().equals("Choose Warrior")) && !Main.inFight) {
				conn = new ConnectionDB();
				boolean bool = conn.conection(panel1.getTx1().getText(), panel1.getTx2().getText());
				if (bool) {
					//Take the second warrior for CPU one time
					if (!Main.cpu_player_bool) {
						
						ArrayList<Warrior> warriors2 = conn.getWarriorContainer();
						Main.warriorCPU = warriors2.get((int) Math.floor(Math.random()*(warriors2.size()-2+1)+1));
						ArrayList<Weapon> weapons2 = conn.getWeaponContainer(warriorCPU);
						Main.weaponCPU = weapons2.get((int) Math.floor(Math.random()*(weapons2.size()-2+1)+1));
						Main.cpu_player_bool = true;
					}
					
					//============
					if (panel4 != null) {
						remove(panel4);
					}else if (panel1 != null) {
						remove(panel1);
					}
					
					setTitle("Choose Warrior");
					setSize( 900, 600);
					setLocationRelativeTo(null);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					panel2 = new ChooseWarrior_Window(conn.getWarriorContainer());
					for (JButton boton : panel2.getButtonArray()) {
						boton.addActionListener(new activeBotons());
					}
					add(panel2);
					

				}
				
				
			}
			//Coger Weapon si es seguido de el panel4
			if (e.getActionCommand().contentEquals("Choose Weapon") && !Main.inFight) {
				
				
				
				if (panel4 != null) {
					remove(panel4);
				}
				setTitle("Choose Weapon");
				setSize( 900, 650);
				setLocationRelativeTo(null);
				createPanel3();
				
				
			}
			//Coger weapon si es de seguido de escoge warrior
			for (Warrior warrior : conn.getWarriorContainer()) {
				
				if (warrior.getWarrior_name().equals(e.getActionCommand())) {
					
					
					if (panel2 != null) {
						
						Main.warriorPlayer = warrior;
						
						remove(panel2);
					}
					
					setTitle("Choose Weapon");
					setSize( 900, 650);
					setLocationRelativeTo(null);
					createPanel3();
					break;
				}
			}
			
			//Fight
			if (e.getActionCommand().contentEquals("Fight")) {
				
				
				Player user = new Player(warriorPlayer,weaponPlayer);
				Player bot = new Player(warriorCPU,weaponCPU);
				algoritmBattle(user,bot);
			}
			//Clear Console
			if (e.getActionCommand().contentEquals("Clear Console")) {
				
				panel4.getTerminal().setText("");
				
			}
			//Ranking
			if (e.getActionCommand().equals("Rankings")) {
				new Tabla(12,"Ranking Players");
			}
			//Si le da a cambiar weapon o warrior en medio de lucha
			if ((e.getActionCommand().equals("Choose Warrior")||e.getActionCommand().contentEquals("Choose Weapon"))&&Main.inFight) {
				if (e.getActionCommand().equals("Choose Warrior")) {
					JOptionPane.showMessageDialog(null, "You cannot pick a warrior if you are fighting", "Error", JOptionPane.WARNING_MESSAGE);
				}
				if (e.getActionCommand().equals("Choose Weapon")) {
					JOptionPane.showMessageDialog(null, "You cannot pick a weapon if you are fighting", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		//Funcion para cuando vayas a meter el panel3
		public void createPanel3() {
			panel3 = new ChooseWeapon_Panel(Main.warriorPlayer,conn);
			for (JButton boton : panel3.getButtonArray()) {
				
				boton.addActionListener( new activeBotons() {
					

					public void actionPerformed(ActionEvent e) {
						for (Weapon weapon : conn.getWeaponContainer(Main.warriorPlayer)) {
							if (weapon.getWeapon_name().equals(e.getActionCommand())) {
								Main.weaponPlayer = weapon;
							}
						}
						
						//Panel de batalla
						
						remove(panel3);
						
						setTitle("Battle");
						setSize(940, 790);
						setLocationRelativeTo(null);
						panel4 = new BattleWindow(panel1.getUsername().getText(),Main.warriorPlayer, Main.weaponPlayer,Main.warriorCPU, Main.weaponCPU);
						JButton JCWarrior = panel4.getJCWarrior();
						JButton JCWeapon = panel4.getJCWeapon();
						JButton JRanking = panel4.getJRanking();
						JButton  botonFight= panel4.getBotonFight();
						JButton botonClearConsole = panel4.getBotonClearConsole();
						
						JCWarrior.addActionListener(new activeBotons());
						JCWeapon.addActionListener(new activeBotons());
						JRanking.addActionListener(new activeBotons());
						botonFight.addActionListener(new activeBotons());
						botonClearConsole.addActionListener(new activeBotons());
						add(panel4);
						
					}
				});
			}
			add(panel3);
			
		}
		
	}
	

}


//Clase para ventana de el Ranking
class Tabla extends JFrame{
	public Tabla(int rows,String nombre) {
		//==Confg general
		Container cn = this.getContentPane();
		this.setTitle(nombre);
		setSize(500, 200);
		setLocationRelativeTo(null);
		
		
		JPanel panel1 = new JPanel();
		
		String[] nomCol = {"ID","NOMBRE","DESCRIPCION","DINERO","PUNTOS","NIVEL"};
		
//////		String[][] datos = conn.buscar(nombre, rows);
//////		JTable taula = new JTable(datos,nomCol);
////		taula.setRowHeight(30);
////		
////		taula.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
////		
////		JScrollPane jsp = new JScrollPane(taula);
//		panel1.add(jsp);
		cn.setLayout(new BoxLayout(cn, BoxLayout.Y_AXIS));
		
		JLabel titulo = new JLabel(nombre);
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		add(titulo);
		add(panel1);
		
		
		setVisible(true);
	}
}
