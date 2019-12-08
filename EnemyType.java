/**This class sets up the possible ENUM types the enemy can be
 * @author Toby Lane
 */
public enum EnemyType{
	THIEF ("Thief"),
	GOBLIN ("Goblin"),
	CRAZIEDWOLF ("Crazied"),
	CRAZIEDHIPPIE ("Crazied Hippie"),
	ZOMBIE ("Zombie");

	//testing to get enum types to strings for save/restore methods
	private final String name;
	/**Method that returns the type as a String
	 * @return The EnemyType as a string
	 */
	private EnemyType(String s){
		name = s;
	}
	/**Method that checks to see if the name of another enemy matches our enemy
	 * @param otherName The name of the enemy you are checking if it matches
	 * @return True if the names match, otherwise false
	 */
	public boolean equalsName(String otherName){
		return name.equals(otherName);
	}
	/**Method that returns the type as a String
	 * @return The type of the enemy as a string
	 */
	public String toString(){
		return this.name;
	}

}
