import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//Mirar de poner el disenno mas bonito
public class ChooseWarrior_Window extends JPanel{
	private  ArrayList<Warrior> array;
	private  ArrayList<JButton> ButtonArray = new ArrayList<JButton>();
	public ChooseWarrior_Window(WarriorContainer warriorContainer) {
		this.array = warriorContainer.getWarriors();
		JLabel title = new JLabel("Choose the Warrior");
		add(title);
		title.setFont(new Font("Arial",Font.ITALIC|Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		add(Box.createVerticalGlue());
		//
		ArrayList<Warrior> Human = warriorContainer.getHumans();
		ArrayList<Warrior> Elf = warriorContainer.getElf();
		ArrayList<Warrior> Dwarf = warriorContainer.getDwarf();
		
		Race_panel humanPanel = new Race_panel(Human,"Human");
		for (JButton boton:humanPanel.getBotones()) {
			
			this.ButtonArray.add(boton);
			boton.setOpaque(true);
			boton.setBackground(Color.cyan);
		}
		Race_panel elfPanel = new Race_panel(Elf,"Elf");
		for (JButton boton:elfPanel.getBotones()) {
			this.ButtonArray.add(boton);
			boton.setOpaque(true);
			boton.setBackground(Color.green);
		}
		Race_panel dwarfPanel = new Race_panel(Dwarf,"Dwarf");
		for (JButton boton:dwarfPanel.getBotones()) {
			this.ButtonArray.add(boton);
			boton.setOpaque(true);
			boton.setBackground(Color.orange);
		}
		add(humanPanel);
		add(elfPanel);
		add(dwarfPanel);
		//
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
	}
	
	public ArrayList<JButton> getButtonArray() {
		return ButtonArray;
	}

}
class Race_panel extends JPanel{
	
	private  ArrayList<Warrior> array;
	private ArrayList<JButton> botones = new ArrayList<JButton>();
	public Race_panel(ArrayList<Warrior> array,String race) {
		
		add(new JLabel(race));
		//Annadir lo botones
		for (Warrior warrior : array) {
				
			String sCarpAct = System.getProperty("user.dir");
			Toolkit mipantalla = Toolkit.getDefaultToolkit();
			Image miicono = mipantalla.getImage(sCarpAct+File.separator+"imagenes"+File.separator+warrior.getImg());
			
			
			Image dimg = miicono.getScaledInstance(100, 150,
			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			JButton boton = new JButton(warrior.getWarrior_name(),imageIcon);
			boton.setToolTipText("<html><p>HP: " + warrior.getHealth()
            + "<br>Damage: " + warrior.getForce()
            + "<br>Defense: " + warrior.getDefense()
            + "<br>Agility: " + warrior.getAgility()
            + "<br>Speed: " + warrior.getVelocity()+"</p></hmtl>");
			botones.add(boton);
			add(boton);
				
		}
	}
	public ArrayList<JButton> getBotones() {
		return botones;
	}
	
	
	

}

