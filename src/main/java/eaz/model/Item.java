package eaz.model;

public class Item {
    private String type;
    private String name;
    private String description;
    private String location;
    private int modifier;

    
    // public getters
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getModifier() {
        return modifier;
    }

    
    // public wrapper class for all items:
    public static class ItemList{
        private Item[] items;

        public Item[] getItems() {
            return items;
        }
    }

    /* To call items do:
            Item.ItemList itemList = JsonReader.readJson(Item.ItemList.class);
        Item[] items = itemList.getItems();

        for(Item item : items){
            System.out.println("Type: " + item.getType());
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Location: " + item.getLocation());
            System.out.println("Modifier: " + item.getModifier());
            System.out.println();
        }
     */
}

