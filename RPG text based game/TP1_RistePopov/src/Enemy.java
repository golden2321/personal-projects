/*
The Enemy class is used to create an instance of an enemy that is going to do fight heros and get stronger every time
a hero beats them.
 */
public class Enemy {
    private int health;
    private int attack;
    private int experienceGiven;

    //The constructor for the Enemy class takes an integer level, which also represents how many enemies have been beat.
    public Enemy(int level){
        // enemies get stronger and give more experience with a higher level
        health = 100 + 10 * level;
        attack = 25 + 5 * level;
        experienceGiven = 35 + 8 * level;
    }

    //getters
    public int getHealth() {
        return health;
    }
    public int getExperienceGiven() {
        return experienceGiven;
    }

    // fight handling
    public void receiveDamage(int damage){
        health = health - damage;
    }
    public  void doDamage(Hero hero){
        hero.receiveDamage(attack);
    }
}
