package eaz.model;

import com.google.gson.annotations.Expose;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player {
    @Expose
    private final String name = "Edgar Allen Zombie";
    @Expose
    private final List<String> inventory = new LinkedList<>();
    @Expose
    private int health;  // starting health value for player
    @Expose
    private int armor;
    @Expose
    private int damage;
    @Expose
    private int hitChance;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int damage) {

        health -= damage;
    }

    public int getArmor() {
        return armor;
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