package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Character implements Interactable {

    protected String name;
    protected String description;
    protected CharacterStats stats;

    protected int inventorySize;
    protected List<String> inventory;

    public interface InventoryListener {

        default void onItemAdded(Character character, String itemName) {}

        default void onItemAddFailed(Character character, String itemName) {}

        default void onItemRemoved(Character character, String itemName) {}

        default void onSizeChanged(Character character, int oldSize, int newSize) {}
    }

    /*
    Constructors
     */

    public Character(String name, String description, int inventorySize) {
        this.name = name;
        this.description = description;
        this.inventorySize = inventorySize;
        this.inventory = new ArrayList<>();
    }


    /*
    Character inventory
     */
    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize, InventoryListener listener) {
        int oldSize = this.inventorySize;
        this.inventorySize = inventorySize;
        listener.onSizeChanged(this, oldSize, this.inventorySize);
    }

    public List<String> getInventory() {
        return new ArrayList<>(inventory);
    }

    public void removeItemFromInventory(String itemName, InventoryListener listener) {
        boolean result = inventory.remove(itemName);
        if (result) listener.onItemRemoved(this, itemName);
    }

    public void addItemToInventory(String itemName, InventoryListener listener) {
        if (inventorySize < inventory.size() - 2) {
            inventory.add(itemName);
            listener.onItemAdded(this, itemName);
        } else {
            listener.onItemAddFailed(this, itemName);
        }
    }

    public void addAllToInventory(Collection<String> items, InventoryListener listener) {
        items.forEach(i -> addItemToInventory(i, listener));
    }

    public void removeAllFromInventory(Collection<String> items, InventoryListener listener) {
        items.forEach(i -> removeItemFromInventory(i, listener));
    }

    public void addItemToInventory(Item item, InventoryListener listener) {
        addItemToInventory(item.getName(), listener);
    }

    public void removeItemFromInventory(Item item, InventoryListener listener) {
        removeItemFromInventory(item.getName(), listener);
    }

    /*
    Getters & Setters
     */

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

    public CharacterStats getCharacterStats() {
        return stats;
    }
}
