package main.interfaces;

public interface Damageable {

    void dealDamage(Damageable entity);
    void takeDamage(Damageable source);

    void onDeath();


}
