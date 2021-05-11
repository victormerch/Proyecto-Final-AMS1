
public class Weapon {
	private int weapon_id;
	private String weapon_name;
	private String weapon_url;
	private int weapon_point;
	private int plus_velocity;
	private int plus_force;
	private String id_race;
	public Weapon(int weapon_id, String weapon_name, String weapon_url, int weapon_point, int plus_velocity,
			int plus_force, String id_race) {
		super();
		
		this.weapon_id = weapon_id;
		this.weapon_name = weapon_name;
		this.weapon_url = weapon_url;
		this.weapon_point = weapon_point;
		this.plus_velocity = plus_velocity;
		this.plus_force = plus_force;
		this.id_race = id_race;
	}
	public int getWeapon_id() {
		return weapon_id;
	}
	public void setWeapon_id(int weapon_id) {
		this.weapon_id = weapon_id;
	}
	public String getWeapon_name() {
		return weapon_name;
	}
	public void setWeapon_name(String weapon_name) {
		this.weapon_name = weapon_name;
	}
	public String getWeapon_url() {
		return weapon_url;
	}
	public void setWeapon_url(String weapon_url) {
		this.weapon_url = weapon_url;
	}
	public int getWeapon_point() {
		return weapon_point;
	}
	public void setWeapon_point(int weapon_point) {
		this.weapon_point = weapon_point;
	}
	public int getPlus_velocity() {
		return plus_velocity;
	}
	public void setPlus_velocity(int plus_velocity) {
		this.plus_velocity = plus_velocity;
	}
	public int getPlus_force() {
		return plus_force;
	}
	public void setPlus_force(int plus_force) {
		this.plus_force = plus_force;
	}
	public String getId_race() {
		return id_race;
	}
	public void setId_race(String id_race) {
		this.id_race = id_race;
	}
	
	
	
	 
}
