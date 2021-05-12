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

public class ChooseWarrior_Window extends JPanel{
	private  ArrayList<Warrior> array;
	private  ArrayList<JButton> ButtonArray = new ArrayList<JButton>();
	public ChooseWarrior_Window(ArrayList<Warrior> array) {
		this.array = array;
		ArrayList<Warrior> Human = new ArrayList<Warrior>();
		ArrayList<Warrior> Elf = new ArrayList<Warrior>();
		ArrayList<Warrior> Dwarf = new ArrayList<Warrior>();
		
		JLabel title = new JLabel("Choose the Warrior");
		add(title);
		title.setFont(new Font("Arial",Font.ITALIC|Font.BOLD,24));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		add(Box.createVerticalGlue());
		//
		for (Warrior warrior : array) {
			if (warrior instanceof Human ) {
				Human.add(warrior);
			}else if (warrior instanceof Elf ) {
				Elf.add(warrior);
			}else if (warrior instanceof Dwarf ) {
				Dwarf.add(warrior);
			}
		}
		Race_panel humanPanel = new Race_panel(Human,"Human");
		for (JButton boton:humanPanel.getBotones()) {
			
			this.ButtonArray.add(boton);
		}
		Race_panel elfPanel = new Race_panel(Elf,"Elf");
		for (JButton boton:elfPanel.getBotones()) {
			
			this.ButtonArray.add(boton);
		}
		Race_panel dwarfPanel = new Race_panel(Dwarf,"Dwarf");
		for (JButton boton:dwarfPanel.getBotones()) {
			
			this.ButtonArray.add(boton);
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
				botones.add(boton);
				add(boton);
				
		}
	}
	public ArrayList<JButton> getBotones() {
		return botones;
	}
	
	
	

}

