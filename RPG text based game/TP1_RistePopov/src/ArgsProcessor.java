/*
The class ArgsProcessor takes the arguments from the terminal entered when java Main is run and processes everything so
that the role playing game works. It creates a hero with the first three elements (name, maxHealth, attack) and executes
all of the actions that come after that.
 */

public class ArgsProcessor {


    public static void process(String[] args) {

        String[] phrase = makePhrase(args[0]);

        Hero hero;

        // Choose Hero type according to first letter
        if(phrase[0].charAt(0) == 'A'){
            hero = new Attack((phrase[0]), Integer.parseInt(phrase[1]),Integer.parseInt(phrase[2]));
        } else if (phrase[0].charAt(0) == 'D') {
            hero = new Defense((phrase[0]), Integer.parseInt(phrase[1]),Integer.parseInt(phrase[2]));
        }
        else{
            hero = new Hero((phrase[0]), Integer.parseInt(phrase[1]),Integer.parseInt(phrase[2]));
        }

        boolean isAlive = true; // hero is alive in the beginning

        // start at the third element (i = 3) and go through all the actions
        for (int i = 3; i < phrase.length; i++) {
            // if hero dies stop doing the actions
            if(doAction(phrase[i], hero) == false){
                isAlive = false;
                break;
            }
        }
        if(isAlive == true){
            System.out.println("In his quest, " + hero.getName() +
                    " beat " + hero.getEnemiesBeat() +
                    " enemies, attained level " + hero.getLevel() +
                    " and survived with " + hero.getHealth() + " HP!");
        }
        else{
            System.out.println("In his quest, " + hero.getName() +
                    " died after beating " + hero.getEnemiesBeat() +
                    " enemies and attaining level " + hero.getLevel() + "!");
        }
    }

    private static String[] makePhrase(String args) {
        return args.trim().split(",");
    }

    // méthode qui prend la partie de la phrase qui décrit l'action et le héros, puis effectue l'action correspondante
    // retourne true si le joueur survit à l'action, false sinon
    private static boolean doAction(String action, Hero hero) {
        // ici, on transforme le String action en un tableau de String, en séparant les mots par des espaces
        String[] phrase = action.trim().split(" ");
        // le type d'action est déterminé par le premier mot de la phrase

        switch (phrase[0]) {

            case "fought":

                int enemies = Integer.parseInt(phrase[1]); // number of enemies

                for (int i = 0; i < enemies; i++) {

                    // create a new enemy everytime an enemy dies
                    Enemy enemy = new Enemy(hero.getEnemiesBeat());

                    // cycle through the attacks until either the enemy or the hero dies
                    while (true){
                        hero.doDamage(enemy);
                        if(enemy.getHealth() <= 0){
                            hero.setEnemiesBeat(hero.getEnemiesBeat() + 1);
                            if(hero.getLevel() < 99){
                                // take the experience of the enemy
                                hero.updateExperience(enemy);
                            }
                            break;
                        }
                        enemy.doDamage(hero);
                        if(hero.getHealth() <= 0){
                            // if hero dies can't keep fighting
                            return false;
                        }
                    }

                }
                break;

            case "rested":
                // restore all health
                hero.setHealth(hero.getMaxHealth());
                break;

            case "healed":
                // heal some amount n of health
                int health = hero.getHealth() + Integer.parseInt(phrase[1]);

                // if health overflows above maximum, keep at maximum
                if(hero.getMaxHealth() < health){
                    hero.setHealth(hero.getMaxHealth());
                }
                else{
                    hero.setHealth(health);
                }
                break;

            case "trained":
                // increase attack by some amount n
                hero.setAttack(hero.getAttack() + Integer.parseInt(phrase[3]));
                break;
        }

        return true;
    }

}
