import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseWeapon_Panel extends JPanel{
	private  ArrayList<JButton> ButtonArray = new ArrayList<JButton>();
	private  ArrayList<Weapon> WeaponContainer = new ArrayList<Weapon>();
	public ChooseWeapon_Panel(Warrior warrior,ConnectionDB conn) {
		JPanel gridPanel = new JPanel();
		JLabel title = new JLabel("Choose the Weapon of "+warrior.getWarrior_name());
		title.setFont(new Font("Arial",Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		ArrayList<Weapon> array = conn.getWeaponContainer(warrior);
		add(title);
		
		for (Weapon weapon : array) {
			
			String sCarpAct = System.getProperty("user.dir");
			Toolkit mipantalla = Toolkit.getDefaultToolkit();
			Image miicono = mipantalla.getImage(sCarpAct+File.separator+"imagenes"+File.separator+weapon.getWeapon_url());
			Image dimg = miicono.getScaledInstance(100, 150,
			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			JButton boton = new JButton(weapon.getWeapon_name(),imageIcon);
			this.ButtonArray.add(boton);
			gridPanel.add(boton);
		}
		gridPanel.setLayout(new GridLayout(3,3));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(gridPanel);
		
	}
	public ArrayList<JButton> getButtonArray() {
		return ButtonArray;
	}
	public ArrayList<Weapon> getWeaponContainer() {
		return WeaponContainer;
	}
	
	
	
	
}
