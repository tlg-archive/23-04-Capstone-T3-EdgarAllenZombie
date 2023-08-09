package eaz.model;

public class Character {
      private String type;
      private String name;
      private String health;
      private String damage;
      private String description;
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

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
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

    public static class CharacterList{
        private Character[] characters;

        public Character[] getCharacters() {
            return characters;
        }
    }
}