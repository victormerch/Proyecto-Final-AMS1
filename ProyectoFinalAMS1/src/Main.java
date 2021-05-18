import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.mysql.cj.xdevapi.Statement;

public class Main extends JFrame {
	private static Scanner input = new Scanner(System.in);
	// Containers
	private static WarriorContainer warriorContainer ;
	// Boolean for fight
	private static boolean inFight = false;
	private static boolean fightBool = true;
	private static boolean fightfinal = false;
	// Panels
	private static InitialWindow panel1;
	private static ChooseWarrior_Window panel2;
	private static ChooseWeapon_Panel panel3;
	private static BattleWindow panel4;

	// Jugadores
	private static Warrior warriorPlayer;
	private static Weapon weaponPlayer;
	private static Warrior warriorCPU;
	private static Weapon weaponCPU;

	// ConnexionDB and username
	private static ConnectionDB conn;

	private static boolean cpu_player_bool;

	private int contRounds = 0;
	private int contTourn = 0;

	private Player player1 = null, player2 = null;

	// Base de datos
	private int totalPoints = 0;

	// ==Instanciamiento classe
	public static void main(String[] args) {
		new Main();

	}

	public Main() {
		// Cargamos el Frame inicial
		this.setTitle("Welcome");
		setSize(400, 450);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// First Panel
		panel1 = new InitialWindow();
		JButton boton1 = panel1.getButton();

		boton1.addActionListener(new activeBotons());
		add(panel1);

		
		//
		
		setVisible(true);
	}

	// Battle Algorithm
	public void algoritmBattle(Player user, Player bot)  {
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
					if (randomAt == 1) {
						player1 = user;
						player2 = bot;
					}
					if (randomAt == 2) {
						player1 = bot;
						player2 = user;
					}
				}
			}
			System.out.println("Current Total Points: " + totalPoints);
			System.out.println("Beggins: " + player1.getName());
			fightBool = false;
		}
		// RONDAS
		if (bot.getLife() > 0 && user.getLife() > 0) {
			contRounds++;
			System.out.println("~~ Turn: " + contRounds + " ~~");
			if (player1.getUsername() == "CPU") {
				player2.setLife(player2.getLife() - player1.getAtack());
				System.out.println(user.Username + ": HP of " + player2.getName() + " has " + player2.getLife());
				// Color vida CPU
				panel4.getPanelPlayer().setVida(player2.getLife());
				if (player2.getLife() <= (player2.getTotalHP() * 0.5)
						&& player2.getLife() > (player2.getTotalHP() * 0.25)) {
					panel4.getPanelPlayer().setColor(Color.yellow);
				}
				if (player2.getLife() <= (player2.getTotalHP() * 0.25)) {
					panel4.getPanelPlayer().setColor(Color.red);
				}
				//
				if (player2.getLife() > 0) {
					System.out.println(player2.getName() + " has " + player2.getAtack());
					player1.setLife(player1.getLife() - player2.getAtack());
					//
					System.out.println(bot.Username + ": HP of " + player1.getName() + " has " + player1.getLife());

					// Color vida Jugador
					panel4.getPanelCPU().setVida(player1.getLife());
					if (player1.getLife() <= (player1.getTotalHP() * 0.50)
							&& player1.getLife() > (player1.getTotalHP() * 0.25)) {
						panel4.getPanelCPU().setColor(Color.yellow);
					}
					if (player1.getLife() <= (player1.getTotalHP() * 0.25)) {
						panel4.getPanelCPU().setColor(Color.red);
					}
				}
				if (player2.getLife() < 1) {
					System.out.println(player2.getName() + " has been defeated.");
					panel4.getTerminal().setBackground(Color.RED);
					fightfinal = true;
				}
				if (player1.getLife() < 1) {
					System.out.println(player1.getName() + " has been defeated.");
					panel4.getTerminal().setBackground(Color.GREEN);
					fightfinal = true;
				}
				
			} else {
				player2.setLife(player2.getLife() - player1.getAtack());
				System.out.println(bot.Username + ": HP of " + player2.getName() + " has " + player2.getLife());
				// Color vida CPU
				panel4.getPanelCPU().setVida(player2.getLife());
				if (player2.getLife() <= (player2.getTotalHP() * 0.5)
						&& player2.getLife() > (player2.getTotalHP() * 0.25)) {
					panel4.getPanelCPU().setColor(Color.yellow);
				}
				if (player2.getLife() <= (player2.getTotalHP() * 0.25)) {
					panel4.getPanelCPU().setColor(Color.red);
				}
				//
				if (player2.getLife() > 0) {
					// System.out.println(player2.getName() + " hace " + player2.getAtack());
					player1.setLife(player1.getLife() - player2.getAtack());
					//
					System.out.println(user.Username + ": HP of " + player1.getName() + " has " + player1.getLife());

					// Color vida Jugador
					panel4.getPanelPlayer().setVida(player1.getLife());
					if (player1.getLife() <= (player1.getTotalHP() * 0.50)
							&& player1.getLife() > (player1.getTotalHP() * 0.25)) {
						panel4.getPanelPlayer().setColor(Color.yellow);
					}
					if (player1.getLife() <= (player1.getTotalHP() * 0.25)) {
						panel4.getPanelPlayer().setColor(Color.red);
					}
				}
				if (player2.getLife() < 1) {
					System.out.println(player2.getName() + " has been defeated.");
					panel4.getTerminal().setBackground(Color.GREEN);
					fightfinal = true;
				}
				if (player1.getLife() < 1) {
					System.out.println(player1.getName() + " has been defeated.");
					panel4.getTerminal().setBackground(Color.RED);
					fightfinal = true;
				}

			}
		}
		// ACABA LA PARTIDA
		if (fightfinal) {
			// ===Cuando gana algun la partida de hace lo siguiente
			// Cuando pierdes:
			if (player1.getUsername() == "CPU") {
				if (player1.getLife() > player2.getLife()) {
					if(contTourn == 0){
						ConnectionDB.insertPlayer(user.getUsername());
						contTourn ++;
					}
					ConnectionDB.insertBattle(warriorPlayer.getWarrior_name(), weaponPlayer.getWeapon_id(), warriorCPU.getWarrior_name(), weaponCPU.getWeapon_id(), player1.getLife(), player2.getLife());
					if (totalPoints > 0) {
						ConnectionDB.insertRanking(player2.getName(), totalPoints);
					}
					int confirmado = JOptionPane.showConfirmDialog(null,
							"GAMEOVER\nDo you want to play with a different character again?\n" + "You've got: "
									+ totalPoints + " points");
					if (JOptionPane.OK_OPTION == confirmado) {

						remove(panel4);
						setTitle("Choose Warrior");
						setSize(900, 600);
						setLocationRelativeTo(null);
						setDefaultCloseOperation(EXIT_ON_CLOSE);
						panel2 = new ChooseWarrior_Window(warriorContainer);
						for (JButton boton : panel2.getButtonArray()) {
							boton.addActionListener(new activeBotons());
						}
						add(panel2);
					} else {
						new Tabla(conn);
						dispose();
					}

				}
				// Cuando ganas:
				else {
					totalPoints = totalPoints + player2.getLife();
					if(contTourn == 0)
					{
						ConnectionDB.insertPlayer(user.getUsername());
						contTourn ++;
					}
					ConnectionDB.insertBattle(warriorPlayer.getWarrior_name(), weaponPlayer.getWeapon_id(), warriorCPU.getWarrior_name(), weaponCPU.getWeapon_id(), player1.getLife(), player2.getLife());
					int confirmado = JOptionPane.showConfirmDialog(null, "You have won, do you want to play another round?");
					if (JOptionPane.OK_OPTION == confirmado) {
						contTourn = contTourn +1;
						ArrayList<Warrior> warriors2 = conn.getWarriorContainer();
						Main.warriorCPU = warriors2
								.get((int) Math.floor(Math.random() * (warriors2.size() - 2 + 1) + 1));
						ArrayList<Weapon> weapons2 = conn.getWeaponContainer(warriorCPU);
						Main.weaponCPU = weapons2.get((int) Math.floor(Math.random() * (weapons2.size() - 2 + 1) + 1));
						Main.cpu_player_bool = true;

						remove(panel4);

						setTitle("Battle");
						setSize(945, 800);
						setLocationRelativeTo(null);
						panel4 = new BattleWindow(panel1.getUsername().getText(), Main.warriorPlayer, Main.weaponPlayer,
								Main.warriorCPU, Main.weaponCPU);
						JButton JCWarrior = panel4.getJCWarrior();
						JButton JCWeapon = panel4.getJCWeapon();
						JButton JRanking = panel4.getJRanking();
						JButton botonFight = panel4.getBotonFight();
						JButton botonClearConsole = panel4.getBotonClearConsole();

						JCWarrior.addActionListener(new activeBotons());
						JCWeapon.addActionListener(new activeBotons());
						JRanking.addActionListener(new activeBotons());
						botonFight.addActionListener(new activeBotons());
						botonClearConsole.addActionListener(new activeBotons());
						add(panel4);
					}

					else {
						ConnectionDB.insertRanking(player2.getName(), totalPoints);
						new Tabla(conn);
						dispose();
					}

				}
			} else {
				if (player2.getLife() > player1.getLife()) {
					if(contTourn == 0)
					{
						ConnectionDB.insertPlayer(user.getUsername());
						contTourn ++;
					}
					
						ConnectionDB.insertBattle(warriorPlayer.getWarrior_name(), weaponPlayer.getWeapon_id(), warriorCPU.getWarrior_name(), weaponCPU.getWeapon_id(), player2.getLife(), player1.getLife());
					
					if (totalPoints > 0) {
						ConnectionDB.insertRanking(player1.getName(), totalPoints);
					}
					int confirmado = JOptionPane.showConfirmDialog(null,
							"\tGAMEOVER\nDo you want to play with a different character again?\n" + "You've got: "
									+ totalPoints + " points");
					if (JOptionPane.OK_OPTION == confirmado) {
						remove(panel4);
						setTitle("Choose Warrior");
						setSize(900, 600);
						setLocationRelativeTo(null);
						setDefaultCloseOperation(EXIT_ON_CLOSE);
						panel2 = new ChooseWarrior_Window(warriorContainer);
						for (JButton boton : panel2.getButtonArray()) {
							boton.addActionListener(new activeBotons());
						}
						add(panel2);
					} else {
						ConnectionDB.insertRanking(player1.getName(), totalPoints);
						new Tabla(conn);
						dispose();
					}

				}
				// Cuando ganas:
				else {
					totalPoints = totalPoints + player1.getLife();
					if(contTourn == 0)
					{
						ConnectionDB.insertPlayer(user.getUsername());
						contTourn ++;
					}
					ConnectionDB.insertBattle(warriorPlayer.getWarrior_name(), weaponPlayer.getWeapon_id(), warriorCPU.getWarrior_name(), weaponCPU.getWeapon_id(), player2.getLife(), player1.getLife());
					int confirmado = JOptionPane.showConfirmDialog(null, "You have won, do you want to play another round?");
					if (JOptionPane.OK_OPTION == confirmado) {
						contTourn = contTourn +1;
						ArrayList<Warrior> warriors2 = conn.getWarriorContainer();
						Main.warriorCPU = warriors2
								.get((int) Math.floor(Math.random() * (warriors2.size() - 2 + 1) + 1));
						ArrayList<Weapon> weapons2 = conn.getWeaponContainer(warriorCPU);
						Main.weaponCPU = weapons2.get((int) Math.floor(Math.random() * (weapons2.size() - 2 + 1) + 1));
						Main.cpu_player_bool = true;

						remove(panel4);

						setTitle("Battle");
						setSize(940, 795);
						setLocationRelativeTo(null);
						panel4 = new BattleWindow(panel1.getUsername().getText(), Main.warriorPlayer, Main.weaponPlayer,
								Main.warriorCPU, Main.weaponCPU);
						JButton JCWarrior = panel4.getJCWarrior();
						JButton JCWeapon = panel4.getJCWeapon();
						JButton JRanking = panel4.getJRanking();
						JButton botonFight = panel4.getBotonFight();
						JButton botonClearConsole = panel4.getBotonClearConsole();

						JCWarrior.addActionListener(new activeBotons());
						JCWeapon.addActionListener(new activeBotons());
						JRanking.addActionListener(new activeBotons());
						botonFight.addActionListener(new activeBotons());
						botonClearConsole.addActionListener(new activeBotons());
						add(panel4);
					}

					else {
						ConnectionDB.insertRanking(player1.getName(), totalPoints);
						new Tabla(conn);
						dispose();
					}

				}

			}
			contRounds = 0;
			fightBool = true;
			fightfinal = false;
			inFight = false;
		}

	}

	// Events
	class activeBotons implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Coger personajes
			if ((e.getActionCommand().equals("Play") || e.getActionCommand().equals("Choose Warrior"))
					&& !Main.inFight) {
				if (totalPoints > 0) {
					if (player1.getUsername() == "CPU") {
						ConnectionDB.insertRanking(player2.getName(), totalPoints);
					} else {
						ConnectionDB.insertRanking(player1.getName(), totalPoints);
					}
				}
				conn = new ConnectionDB();
				boolean bool = conn.conection(panel1.getTx1().getText(), panel1.getTx2().getText());
				if (panel1.getUsername().getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "To much characters in Username, max 15", "Error", JOptionPane.WARNING_MESSAGE);
					bool = false;
				}
				if (bool) {
					// Take the second warrior for CPU one time
					if (!Main.cpu_player_bool) {

						warriorContainer = new WarriorContainer(conn.getWarriorContainer());
						Main.warriorCPU = warriorContainer.getRandomWarrior();
						
						ArrayList<Weapon> weapons2 = conn.getWeaponContainer(warriorCPU);
						Main.weaponCPU = weapons2.get((int) Math.floor(Math.random() * (weapons2.size() - 2 + 1) + 1));
						Main.cpu_player_bool = true;
						
						
					}

					// ============
					if (panel4 != null) {
						remove(panel4);
					} else if (panel1 != null) {
						remove(panel1);
					}
					totalPoints = 0;
					setTitle("Choose Warrior");
					setSize(900, 600);
					setLocationRelativeTo(null);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					panel2 = new ChooseWarrior_Window(warriorContainer);
					for (JButton boton : panel2.getButtonArray()) {
						boton.addActionListener(new activeBotons());
					}
					add(panel2);

				}

			}
			// Coger Weapon si es seguido de el panel4
			if (e.getActionCommand().contentEquals("Choose Weapon") && !Main.inFight) {

				if (panel4 != null) {
					remove(panel4);
				}
				setTitle("Choose Weapon");
				setSize(900, 650);
				setLocationRelativeTo(null);
				createPanel3();

			}
			// Coger weapon si es de seguido de escoge warrior
			for (Warrior warrior : conn.getWarriorContainer()) {

				if (warrior.getWarrior_name().equals(e.getActionCommand())) {

					if (panel2 != null) {

						Main.warriorPlayer = warrior;

						remove(panel2);
					}

					setTitle("Choose Weapon");
					setSize(900, 650);
					setLocationRelativeTo(null);
					createPanel3();
					break;
				}
			}

			// Fight
			if (e.getActionCommand().contentEquals("Fight")) {

				Player user = new Player(panel1.getUsername().getText(), warriorPlayer, weaponPlayer);
				Player bot = new Player("CPU", warriorCPU, weaponCPU);
				
					algoritmBattle(user, bot);
				
			}
			// Clear Console
			if (e.getActionCommand().contentEquals("Clear Console")) {

				panel4.getTerminal().setText("");

			}
			// Ranking
			if (e.getActionCommand().equals("Rankings")) {
				new Tabla(conn);
			}
			// Si le da a cambiar weapon o warrior en medio de lucha
			if ((e.getActionCommand().equals("Choose Warrior") || e.getActionCommand().contentEquals("Choose Weapon"))
					&& Main.inFight) {
				if (e.getActionCommand().equals("Choose Warrior")) {
					JOptionPane.showMessageDialog(null, "You cannot pick a warrior if you are fighting", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
				if (e.getActionCommand().equals("Choose Weapon")) {
					JOptionPane.showMessageDialog(null, "You cannot pick a weapon if you are fighting", "Error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		// Funcion para cuando vayas a meter el panel3
		public void createPanel3() {
			panel3 = new ChooseWeapon_Panel(Main.warriorPlayer, conn);
			for (JButton boton : panel3.getButtonArray()) {

				boton.addActionListener(new activeBotons() {

					public void actionPerformed(ActionEvent e) {
						for (Weapon weapon : conn.getWeaponContainer(Main.warriorPlayer)) {
							if (weapon.getWeapon_name().equals(e.getActionCommand())) {
								Main.weaponPlayer = weapon;
							}
						}

						// Panel de batalla

						remove(panel3);

						setTitle("Battle");
						setSize(935, 790);
						setLocationRelativeTo(null);
						panel4 = new BattleWindow(panel1.getUsername().getText(), Main.warriorPlayer, Main.weaponPlayer,
								Main.warriorCPU, Main.weaponCPU);
						JButton JCWarrior = panel4.getJCWarrior();
						JButton JCWeapon = panel4.getJCWeapon();
						JButton JRanking = panel4.getJRanking();
						JButton botonFight = panel4.getBotonFight();
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
	
	public Tabla(ConnectionDB conn) {
		//==Confg general
		Container cn = this.getContentPane();
		
		setSize( 480, 440);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel panel1 = new JPanel();
		
		String[] nomCol = {"POSITION","PLAYER","WARRIOR","POINTS"};
		
		
		String[][] datos = conn.buscar(conn.countrows("select * from RANKING limit 10;"));
		JTable taula = new JTable(datos,nomCol);
		taula.setRowHeight(34);
		taula.setRowMargin(WIDTH);
		taula.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JScrollPane jsp = new JScrollPane(taula);
		panel1.add(jsp);
		cn.setLayout(new BoxLayout(cn, BoxLayout.Y_AXIS));
		
		JLabel titulo = new JLabel("Top 10 Players");
		titulo.setForeground(Color.blue); 
		titulo.setFont(new Font("Arial",Font.BOLD,24));
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		
		
		add(titulo);
		
		add(panel1);
		
		
		setVisible(true);
	}

	
}

