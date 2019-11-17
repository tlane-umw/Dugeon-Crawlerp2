//This enum sets up the types of enemies. 
public enum EnemyType{
	THIEF ("Thief"),
	GOBLIN ("Goblin"),
	CRAZIEDWOLF ("Crazied"),
	CRAZIEDHIPPIE ("Crazied Hippie"),
	ZOMBIE ("Zombie");

	private final String name;

	private EnemyType(String s){
		name = s;
	}

	public boolean equalsName(String otherName){
		return name.equals(otherName);
	}

	public String toString(){
		return this.name;
	}

}
