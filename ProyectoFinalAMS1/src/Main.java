import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main extends JFrame{
	private static InitialWindow panel1;
	private static ChooseWarrior_Window panel2;
	private static ChooseWeapon_Panel panel3;
	
	private static Warrior warriorPlayer;
	private static Weapon weaponPlayer;
	
	private static ConnectionDB conn;
	private static String username;
	
	public static void main(String[] args) {
		new Main();
	
	}
	public Main() {
		
		this.setTitle("Welcome");
		this.setBounds(500,300, 400, 450);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//First Windows
		panel1 = new InitialWindow();
		JButton boton1 = panel1.getButton();
		username = panel1.getUsername().getText();
		
		boton1.addActionListener(new activeBotons());
		add(panel1);
		//
		setVisible(true);
	}
	//Events
	class activeBotons implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Play")) {
				conn = new ConnectionDB();
				boolean bool = conn.conection(panel1.getTx1().getText(), panel1.getTx2().getText());
				if (bool) {
					remove(panel1);
					setTitle("Choose Warrior");
					setBounds(400,200, 900, 600);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					panel2 = new ChooseWarrior_Window(conn.getWarriorContainer());
					for (JButton boton : panel2.getButtonArray()) {
						boton.addActionListener(new activeBotons());
					}
					add(panel2);
					

				}
				
			}

			
			for (Warrior warrior : conn.getWarriorContainer()) {
				
				if (warrior.getWarrior_name().equals(e.getActionCommand())) {
					
					warriorPlayer = warrior;
					remove(panel2);
					setTitle("Choose Weapon");
					setBounds(400,200, 900, 650);
					
					panel3 = new ChooseWeapon_Panel(warriorPlayer,conn);
					for (JButton boton : panel3.getButtonArray()) {
						boton.addActionListener(new activeBotons() {
							

							public void actionPerformed(ActionEvent e) {
								for (Weapon weapon : conn.getWeaponContainer(warriorPlayer)) {
									if (weapon.getWeapon_name().equals(e.getActionCommand())) {
										Main.weaponPlayer = weapon;
									}
								}
								
								//Panel de batalla
								remove(panel3);
								setTitle("Battle");
								setBounds(400,200, 900, 655);
								
								BattleWindow panel4 = new BattleWindow(Main.warriorPlayer, Main.weaponPlayer);
								add(panel4);
								
							}
						});
					}
					add(panel3);
					break;
				}
			}
			
			

			
			
			
			
			
		}
		
	}

}
