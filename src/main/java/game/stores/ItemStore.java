package game.stores;

import game.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemStore {

    private List<String> startItems;
    private List<Item> itemList;

    public Map<String, Item> toStringItemMap() {
        Map<String, Item> result = new HashMap<>();
        itemList.forEach(item -> result.put(item.getName(), item));

        return result;
    }
}
