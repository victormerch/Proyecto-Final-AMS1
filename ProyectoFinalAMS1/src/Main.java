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
					setBounds(400,200, 900, 550);
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					ChooseWarrior_Window panel2 = new ChooseWarrior_Window(conn.getWarriorContainer());
					add(panel2);
					//Panel de batalla
//					remove(panel1);
//					setTitle("Battle");
//					setBounds(400,200, 900, 650);
//					setDefaultCloseOperation(EXIT_ON_CLOSE);
//					BattleWindow panel2 = new BattleWindow();
//					add(panel2);
				}
				
			}
			
			
			
			
			
			
		}
		
	}

}
