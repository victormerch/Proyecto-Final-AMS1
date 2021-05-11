import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ConnectionDB {
	public static Connection conn;
	
	
	public ConnectionDB() {
		// TODO Auto-generated constructor stub
	}
	public boolean conection(String user,String pass){
		// TODO Auto-generated method stub
        String urlDatos = "jdbc:mysql://localhost/FINAL_PROYECT?serverTimezone=UTC";
        
        try {
        	//Cargamos el drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver cargado correctamente.");
            //Realizamos conexion con url user y pass
            conn = DriverManager.getConnection(urlDatos,user,pass);
            //JOptionPane.showInputDialog("Introduce la cantidad de numeros que quieres annadir para el promedio");
            return true;
            
            
            
            
        } catch (ClassNotFoundException e) {
        	JOptionPane.showMessageDialog(null, "Driver loaded incorrectly", "Error", JOptionPane.WARNING_MESSAGE);
        	return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Incorrect UserDB or PasswordDB", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
	}
	public ArrayList<Warrior> getWarriorContainer(){
		ArrayList<Warrior> array = new ArrayList<Warrior>();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery("select * from WARRIORS");
			
			while (rs.next()) {
				int race_id = rs.getInt(3);
				ResultSet rs2 = conn.createStatement().executeQuery("select * from RACE where RACE_ID="+race_id);
				rs2.next();
				
				if (race_id == 1) {
					
					array.add(new Dwarf(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4), rs2.getInt(3),rs2.getInt(4), rs2.getInt(5), rs2.getInt(6), rs2.getInt(7)));
				}
				else if (race_id == 2) {
					
					array.add(new Human(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4), rs2.getInt(3),rs2.getInt(4), rs2.getInt(5), rs2.getInt(6), rs2.getInt(7)));
				}
				else if (race_id == 3) {
					
					array.add(new Elf(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getString(4), rs2.getInt(3),rs2.getInt(4), rs2.getInt(5), rs2.getInt(6), rs2.getInt(7)));
				}
				
			}
			return array;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in ArrayWarriors");
			return null;
		}
		
        
		
        
		
		
	}
	
	
	
	
}
