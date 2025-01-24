// Defense is a subclass of Hero and does everything Hero does but receives half the damage and deals half the damage
public class Defense extends Hero{
    public Defense(String name, int health, int attack) {
        super(name, health, attack);
    }
    @Override
    public void receiveDamage(int damage){health = health - damage / 2;}

    @Override
    public  void doDamage(Enemy enemy){enemy.receiveDamage(attack / 2);}

}
