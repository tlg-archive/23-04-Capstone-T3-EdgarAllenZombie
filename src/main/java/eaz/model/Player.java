package eaz.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name = "Edgar Allen Zombie";
    private final List<String> inventory = new LinkedList<>();

    private int health = 50;  // starting health value for player
    private int damage = 3;
    private int hitChance = 5;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public List<String> getInventory() {
        return inventory;
    }

    //we call this function in the textParser for now but have to change in the future. 
    public int increaseHealth(int delta){
     return health += delta;
    }

    public int decreaseHealth(int delta){
        return health -= delta;
    }

    public int increaseDamage(int delta){
        return damage += delta;
    }

    public int decreaseDamage(int delta){
        return damage -= delta;
    }

    public int getHitChance() {
        return hitChance;
    }

    public void setHitChance(int hitChance) {
        this.hitChance = hitChance;
    }

}   // END OF CLASS