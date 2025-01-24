// Attack is a subclass of Hero and does everything Hero does but receives twice the damage and deals twice the damage
public class Attack extends Hero{
    public Attack(String name, int health, int attack) {
        super(name, health, attack);
    }
    @Override
    public void receiveDamage(int damage){
        health = health - damage * 2;
    }

    @Override
    public  void doDamage(Enemy enemy){
        enemy.receiveDamage(attack * 2);
    }

}
