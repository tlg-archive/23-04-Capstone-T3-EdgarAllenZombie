package eaz.model;

import com.google.gson.annotations.Expose;

public class Character {
    @Expose
    private String type;
    @Expose
    private String name;
    @Expose
    private int health;
    @Expose
    private int damage;
    @Expose
    private int armor;
    @Expose
    private String description;
    @Expose
    private String[] dialog;

    public String getType() {
        return type;
    }

    public void setType(String enemy) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int damage) {
        health -= damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String[] getDialog() {
        return dialog;
    }

    public void setDialog(String[] dialog) {
        this.dialog = dialog;
    }

    public static class CharacterList {
        private Character[] characters;

        public Character[] getCharacters() {
            return characters;
        }
    }
}