import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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

	public ChooseWarrior_Window(ArrayList<Warrior> array) {
		ArrayList<Warrior> Human = new ArrayList<Warrior>();
		ArrayList<Warrior> Elf = new ArrayList<Warrior>();
		ArrayList<Warrior> Dwarf = new ArrayList<Warrior>();
		
		JLabel title = new JLabel("Choose the Warrior");
		add(title);
		title.setFont(new Font("Arial",Font.ITALIC|Font.BOLD,24));
		setAlignmentX(CENTER_ALIGNMENT);
		
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
		
		add(new Race_panel(Human,"Human"));
		add(new Race_panel(Elf,"Elf"));
		add(new Race_panel(Dwarf,"Dwarf"));
		//
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
	}

}
class Race_panel extends JPanel{

	public Race_panel(ArrayList<Warrior> array,String race) {
		add(new JLabel(race));
		//Annadir lo botones
		for (Warrior warrior : array) {
			
			Image miicono;
			try {
				
				miicono = ImageIO.read(new File("./axe.png"));
				Image dimg = miicono.getScaledInstance(200, 300,
				        Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				add(new JButton(imageIcon));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error imagenes");
			}

			
		}
	}

}
