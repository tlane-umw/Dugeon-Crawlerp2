    //player fights with enemy if they step on an 'E'
    public boolean fight(){
        Enemy dungeonEnemy = EnemyGenerator.generate();
        boolean playerAlive = true;
        boolean enemyAlive = true;
        boolean didUserWin = false;
        int roundNumber = 1;
        int playerStrength = getInventory().getEquippedWeapon().getStrength();
        int playerDef = getInventory().getEquippedArmor().getStrength();
        //running through the fight until the player or enemy dies
        System.out.println("A battle is about to begin!");
        System.out.println("Press H to Attack");
        System.out.println("Press J to Defend, using a turn, but increasing your defense for the Enemies next attack. You then counterattack for half what they hit you for.");
        System.out.println("Press K to Charge, wasting a turn, but increasing your attack for the next turn.");
        System.out.println("Press L to Reprint options");
        while ((playerAlive == true) && (enemyAlive == true)){
            System.out.println("Round " + roundNumber + " is about to begin!");
            System.out.println();
            try{

                Thread.sleep(2000);
            }
            catch(InterruptedException g){
                System.out.println("Interrupted");
            }
            //player attacking the enemy
            System.out.println("The " + dungeonEnemy.getName() + " currently has a health of " + dungeonEnemy.getHealth());
            System.out.println("What would you like to do?");
            String choices = s.nextLine();
            char choice = choices.charAt(0);
            System.out.println();
            
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException z){
                System.out.println("Interrupted!");
            }
         if(choice == 'H' || choice == 'h'){
            //getting the amount of damage the enemy will take and subtracting that from their current health
            int enemyDamage = playerStrength;
            dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
            System.out.println("The " + dungeonEnemy.getName() + " took "  + enemyDamage + " damage from the players " + getInventory().getEquippedWeapon().getName());
            System.out.println();
            playerStrength = getInventory().getEquippedWeapon().getStrength();
            //checking if the enemy is still alive
            if (dungeonEnemy.getHealth() <= 0){
                didUserWin = true;
                enemyAlive = false;
                return true;
            }

            //printing the enemies health if it is still alive
            else{
                System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException f){
                    System.out.println("Interruputed!");
                }
            }
            //getting players current health before the enemy attacks
            System.out.println("The player currently has a health of " + getHealth());
            System.out.println();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException eee){
                System.out.println("Interrupted!");
            }

            //getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
            int playerDamage = ((dungeonEnemy.getDamage()) - (getInventory().getEquippedArmor().getStrength()));

            //checking if the players current armor is stronger than the enemy's attack
            if (playerDamage <= 0){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException y){
                    System.out.println("Interrupted");
                }
                System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException d){
                    System.out.println("Interrupted!");
                }
            }
            //setting the players new health based on the previously calculated enemy damage
            else{
                int currentPlayerHealth = getHealth();
                int newPlayerHealth = currentPlayerHealth - playerDamage;
                setHealth(newPlayerHealth);
                
                
                System.out.println("You took " + playerDamage + " damage from the enemy!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException c){
                    System.out.println("Interrupted!");
                }

                //cheching if the player is still alive
                if (getHealth() <= 0){
                    didUserWin = false;
                    System.out.println("The enemy brutally murdered you!");
                    System.out.println("Better luck next time!");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException b){
                        System.out.println("Interrupted!");
                    }
                    System.exit(0);
                    System.out.println();
                    playerAlive = false;
                }
                else{
                    //printing out players health after enemy attack
                    System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
                    System.out.println();
                    try{
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException a){
                        System.out.println("Interrupted!");
                    }
                }
            }

          }else if(choice == 'J' || choice == 'j'){
            //getting players current health before the enemy attacks
            System.out.println("The player currently has a health of " + getHealth());
            System.out.println();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException eee){
                System.out.println("Interrupted!");
            }
            //getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
            int playerDamage = ((dungeonEnemy.getDamage()) - (playerDef + 5));

            //checking if the players current armor is stronger than the enemy's attack
            if (playerDamage <= 0){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException y){
                    System.out.println("Interrupted");
                }
                System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException d){
                    System.out.println("Interrupted!");
                }
            }
            //setting the players new health based on the previously calculated enemy damage
            else{
                int currentPlayerHealth = getHealth();
                int newPlayerHealth = currentPlayerHealth - playerDamage;
                setHealth(newPlayerHealth);
                int enemyDamage = playerDamage / 2;
                System.out.println("You took " + playerDamage + " damage from the enemy!");
                dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
                System.out.println("You counter attacked and did " + enemyDamage);
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException c){
                    System.out.println("Interrupted!");
                }
                //checking if the enemy is still alive
               if (dungeonEnemy.getHealth() <= 0){
                didUserWin = true;
                enemyAlive = false;
                return true;
            }//printing the enemies health if it is still alive
            else{
                System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException f){
                    System.out.println("Interruputed!");
                }
            }
                //cheching if the player is still alive
                if (getHealth() <= 0){
                    didUserWin = false;
                    System.out.println("The enemy brutally murdered you!");
                    System.out.println("Better luck next time!");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException b){
                        System.out.println("Interrupted!");
                    }
                    System.exit(0);
                    System.out.println();
                    playerAlive = false;
                }
                else{
                    //printing out players health after enemy attack
                    System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
                    System.out.println();
                    try{
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException a){
                        System.out.println("Interrupted!");
                    }
                }
            }
          }else if(choice == 'K' || choice == 'k'){
            playerStrength += 15;
            //checking if the enemy is still alive
            if (dungeonEnemy.getHealth() <= 0){
                didUserWin = true;
                enemyAlive = false;
                return true;
            }
            //printing the enemies health if it is still alive
            else{
                System.out.println("The new enemy health is still " + dungeonEnemy.getHealth());
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException f){
                    System.out.println("Interruputed!");
                }
            }
            //getting players current health before the enemy attacks
            System.out.println("The player currently has a health of " + getHealth());
            System.out.println();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException eee){
                System.out.println("Interrupted!");
            }
            //getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
            int playerDamage = ((dungeonEnemy.getDamage()) - (playerDef));

            //checking if the players current armor is stronger than the enemy's attack
            if (playerDamage <= 0){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException y){
                    System.out.println("Interrupted");
                }
                System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException d){
                    System.out.println("Interrupted!");
                }
            }
            //setting the players new health based on the previously calculated enemy damage
            else{
                int currentPlayerHealth = getHealth();
                int newPlayerHealth = currentPlayerHealth - playerDamage;
                setHealth(newPlayerHealth);

                System.out.println("You took " + playerDamage + " damage from the enemy!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException c){
                    System.out.println("Interrupted!");
                }

                //cheching if the player is still alive
                if (getHealth() <= 0){
                    didUserWin = false;
                    System.out.println("The enemy brutally murdered you!");
                    System.out.println("Better luck next time!");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException b){
                        System.out.println("Interrupted!");
                    }
                    System.exit(0);
                    System.out.println();
                    playerAlive = false;
                }
                else{
                    //printing out players health after enemy attack
                    System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
                    System.out.println();
                    try{
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException a){
                        System.out.println("Interrupted!");
                    }
                }
            }
          }else if(choice == 'L' || choice == 'l'){
            System.out.println("Press H to Attack");
            System.out.println("Press J to Defend, using a turn, but increasing your defense for the Enemies next attack.");
            System.out.println("Press K to Charge, using a turn, but increasing your attack for the next turn.");
            System.out.println("Press L to Reprint options");
          }else{
            System.out.println("Thats not a choice! Try again!");
          }
          roundNumber = roundNumber + 1;
        }
        return didUserWin;
    }

    //player fights with enemy if they step on an 'E'
    public boolean fight(){
        Enemy dungeonEnemy = EnemyGenerator.generate();
        boolean playerAlive = true;
        boolean enemyAlive = true;
        boolean didUserWin = false;
        int roundNumber = 1;
        int playerStrength = getInventory().getEquippedWeapon().getStrength();
        int playerDef = getInventory().getEquippedArmor().getStrength();
        //running through the fight until the player or enemy dies
        System.out.println("A battle is about to begin!");
        System.out.println("Press H to Attack");
        System.out.println("Press J to Defend, using a turn, but increasing your defense for the Enemies next attack. You then counterattack for half what they hit you for.");
        System.out.println("Press K to Charge, wasting a turn, but increasing your attack for the next turn.");
        System.out.println("Press L to Reprint options");
        while ((playerAlive == true) && (enemyAlive == true)){
            System.out.println("Round " + roundNumber + " is about to begin!");
            System.out.println();
            try{

                Thread.sleep(2000);
            }
            catch(InterruptedException g){
                System.out.println("Interrupted");
            }
            //player attacking the enemy
            System.out.println("The " + dungeonEnemy.getName() + " currently has a health of " + dungeonEnemy.getHealth());
            System.out.println("What would you like to do?");
            String choices = s.nextLine();
            char choice = choices.charAt(0);
            System.out.println();
            
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException z){
                System.out.println("Interrupted!");
            }
         if(choice == 'H' || choice == 'h'){
            //getting the amount of damage the enemy will take and subtracting that from their current health
            int enemyDamage = playerStrength;
            dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
            System.out.println("The " + dungeonEnemy.getName() + " took "  + enemyDamage + " damage from the players " + getInventory().getEquippedWeapon().getName());
            System.out.println();
            playerStrength = getInventory().getEquippedWeapon().getStrength();
            //checking if the enemy is still alive
            if (dungeonEnemy.getHealth() <= 0){
                didUserWin = true;
                enemyAlive = false;
                return true;
            }

            //printing the enemies health if it is still alive
            else{
                System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException f){
                    System.out.println("Interruputed!");
                }
            }
            //getting players current health before the enemy attacks
            System.out.println("The player currently has a health of " + getHealth());
            System.out.println();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException eee){
                System.out.println("Interrupted!");
            }

            //getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
            int playerDamage = ((dungeonEnemy.getDamage()) - (getInventory().getEquippedArmor().getStrength()));

            //checking if the players current armor is stronger than the enemy's attack
            if (playerDamage <= 0){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException y){
                    System.out.println("Interrupted");
                }
                System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException d){
                    System.out.println("Interrupted!");
                }
            }
            //setting the players new health based on the previously calculated enemy damage
            else{
                int currentPlayerHealth = getHealth();
                int newPlayerHealth = currentPlayerHealth - playerDamage;
                setHealth(newPlayerHealth);
                
                
                System.out.println("You took " + playerDamage + " damage from the enemy!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException c){
                    System.out.println("Interrupted!");
                }

                //cheching if the player is still alive
                if (getHealth() <= 0){
                    didUserWin = false;
                    System.out.println("The enemy brutally murdered you!");
                    System.out.println("Better luck next time!");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException b){
                        System.out.println("Interrupted!");
                    }
                    System.exit(0);
                    System.out.println();
                    playerAlive = false;
                }
                else{
                    //printing out players health after enemy attack
                    System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
                    System.out.println();
                    try{
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException a){
                        System.out.println("Interrupted!");
                    }
                }
            }

          }else if(choice == 'J' || choice == 'j'){
            //getting players current health before the enemy attacks
            System.out.println("The player currently has a health of " + getHealth());
            System.out.println();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException eee){
                System.out.println("Interrupted!");
            }
            //getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
            int playerDamage = ((dungeonEnemy.getDamage()) - (playerDef + 5));

            //checking if the players current armor is stronger than the enemy's attack
            if (playerDamage <= 0){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException y){
                    System.out.println("Interrupted");
                }
                System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException d){
                    System.out.println("Interrupted!");
                }
            }
            //setting the players new health based on the previously calculated enemy damage
            else{
                int currentPlayerHealth = getHealth();
                int newPlayerHealth = currentPlayerHealth - playerDamage;
                setHealth(newPlayerHealth);
                int enemyDamage = playerDamage / 2;
                System.out.println("You took " + playerDamage + " damage from the enemy!");
                dungeonEnemy.setHealth((dungeonEnemy.getHealth()) - enemyDamage);
                System.out.println("You counter attacked and did " + enemyDamage);
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException c){
                    System.out.println("Interrupted!");
                }
                //checking if the enemy is still alive
               if (dungeonEnemy.getHealth() <= 0){
                didUserWin = true;
                enemyAlive = false;
                return true;
            }//printing the enemies health if it is still alive
            else{
                System.out.println("The new enemy health is " + dungeonEnemy.getHealth());
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException f){
                    System.out.println("Interruputed!");
                }
            }
                //cheching if the player is still alive
                if (getHealth() <= 0){
                    didUserWin = false;
                    System.out.println("The enemy brutally murdered you!");
                    System.out.println("Better luck next time!");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException b){
                        System.out.println("Interrupted!");
                    }
                    System.exit(0);
                    System.out.println();
                    playerAlive = false;
                }
                else{
                    //printing out players health after enemy attack
                    System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
                    System.out.println();
                    try{
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException a){
                        System.out.println("Interrupted!");
                    }
                }
            }
          }else if(choice == 'K' || choice == 'k'){
            playerStrength += 15;
            //checking if the enemy is still alive
            if (dungeonEnemy.getHealth() <= 0){
                didUserWin = true;
                enemyAlive = false;
                return true;
            }
            //printing the enemies health if it is still alive
            else{
                System.out.println("The new enemy health is still " + dungeonEnemy.getHealth());
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException f){
                    System.out.println("Interruputed!");
                }
            }
            //getting players current health before the enemy attacks
            System.out.println("The player currently has a health of " + getHealth());
            System.out.println();
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException eee){
                System.out.println("Interrupted!");
            }
            //getting the amount of damage the enemy caused based off the players current equipped armor and the enemys damage
            int playerDamage = ((dungeonEnemy.getDamage()) - (playerDef));

            //checking if the players current armor is stronger than the enemy's attack
            if (playerDamage <= 0){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException y){
                    System.out.println("Interrupted");
                }
                System.out.println("The players " + getInventory().getEquippedArmor().getName() + " completely negated the enemy's attack!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException d){
                    System.out.println("Interrupted!");
                }
            }
            //setting the players new health based on the previously calculated enemy damage
            else{
                int currentPlayerHealth = getHealth();
                int newPlayerHealth = currentPlayerHealth - playerDamage;
                setHealth(newPlayerHealth);

                System.out.println("You took " + playerDamage + " damage from the enemy!");
                System.out.println();
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException c){
                    System.out.println("Interrupted!");
                }

                //cheching if the player is still alive
                if (getHealth() <= 0){
                    didUserWin = false;
                    System.out.println("The enemy brutally murdered you!");
                    System.out.println("Better luck next time!");
                    try{
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException b){
                        System.out.println("Interrupted!");
                    }
                    System.exit(0);
                    System.out.println();
                    playerAlive = false;
                }
                else{
                    //printing out players health after enemy attack
                    System.out.println("After the enemy's latest attack, the player's health is " + getHealth());
                    System.out.println();
                    try{
                        Thread.sleep(2000);
                    }
                    catch(InterruptedException a){
                        System.out.println("Interrupted!");
                    }
                }
            }
          }else if(choice == 'L' || choice == 'l'){
            System.out.println("Press H to Attack");
            System.out.println("Press J to Defend, using a turn, but increasing your defense for the Enemies next attack.");
            System.out.println("Press K to Charge, using a turn, but increasing your attack for the next turn.");
            System.out.println("Press L to Reprint options");
          }else{
            System.out.println("Thats not a choice! Try again!");
          }
          roundNumber = roundNumber + 1;
        }
        return didUserWin;
    }


