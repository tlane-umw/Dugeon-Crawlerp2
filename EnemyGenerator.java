import java.util.Random;
import java.util.*;
import java.util.stream.IntStream;
//This class holds the generate method in order to generate an item. 
public class EnemyGenerator{
	// 	This method is a dozy. It generates a new item using Random numbers and Arrays of items.
	// It gets its first random number, which decides which item type it is going to be. 
	// It then generates the name of the item with random numbers. It then uses random numbers to generate a strength, weight, and value.
	// If it is not a weapon or armor however, it does not have a strength.
	// Once the item is generated, it returns an item.
	public static Enemy generate(){
		Enemy newOne = null;
		EnemyType type = null;
		int health = 0;
		int damage = 0;
		String name = "";
		Random rng = new Random();
		int types = rng.nextInt(5);
		int[] strengths  = IntStream.range(1, 50).toArray();
		Item drop = null;
		if(types == 0){
			type = EnemyType.THIEF;
			name = "Slime";
			health = rng.nextInt(10) + 10;
			damage = rng.nextInt(10) + 1;
			drop = ItemGenerator.generate();
		}else if(types == 1){
			type = EnemyType.GOBLIN;
			name = "Goblin";
			health = rng.nextInt(20)+10;	
			damage = rng.nextInt(20)+10;
			drop = ItemGenerator.generate();
		}else if(types == 2){	
			type = EnemyType.CRAZIEDWOLF;
			name = "Crazied Wolf";
			health = rng.nextInt(30)+20;
			damage = rng.nextInt(30)+20;
			drop = ItemGenerator.generate();
		}else if(types == 3){
			type = EnemyType.CRAZIEDHIPPIE;
			name = "Crazied Hippie";
			health = rng.nextInt(35)+25;
			damage = rng.nextInt(35)+25;
			drop = ItemGenerator.generate();
		}else if(types == 4){
			type = EnemyType.ZOMBIE;
			name = "Zombie";
			health = rng.nextInt(45)+25;
			damage = rng.nextInt(45)+35;
			drop = ItemGenerator.generate();
		}
		newOne = new Enemy(type, name, health, damage, drop);
		return newOne;

	}
}

