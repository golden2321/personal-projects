/*
The hero class is used to create an instance of a hero that is going to do certain actions: fight, rest, heal, train.
 */

public class Hero {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int level;
    protected int experience;
    protected int attack;
    protected int enemiesBeat;

    public Hero(String name, int health, int attack){
        this.health = health;
        this.attack = attack;
        this.name = name;
        maxHealth = health;
        level = 1;
        experience = 0;
        enemiesBeat = 0;
    }

    //getters
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getLevel() {
        return level;
    }

    public int getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }

    public int getEnemiesBeat() {
        return enemiesBeat;
    }

    //setters
    public void setHealth(int health) {
        this.health = health;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public void setEnemiesBeat(int enemiesBeat) {
        this.enemiesBeat = enemiesBeat;
    }

    // fight handling
    public void receiveDamage(int damage){
        health = health - damage;
    }
    public  void doDamage(Enemy enemy){
        enemy.receiveDamage(attack);
    }

    /*
    The method updateExperience takes as a parameter an object Enemy and uses its attribute experienceGiven to update
    the experience the hero has. It also handles level update if there is enough experience gained.
     */
    public void updateExperience(Enemy enemy){

        experience = experience + enemy.getExperienceGiven();

        int exp_required;
        exp_required = (int) Math.ceil(50 + (level + 1) * 20 * (Math.pow(1.1, ( level + 1))));

        // handle level update
        if (experience >= exp_required){
            // update values if reached next level
            experience = 0;
            level = level + 1;
            maxHealth = maxHealth + 12;
            attack = attack + 6;
            health = maxHealth;
        }

    }

}
