package game;

import java.util.List;
import java.util.Map;

public class ItemStore {

    private List<String> startItems;
    private Map<String, Item> items;

    public List<String> getStartItems() {
        return startItems;
    }

    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    public Item getItemByName(String itemName) {
        return items.get(itemName);
    }
}
