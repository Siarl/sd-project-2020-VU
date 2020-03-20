package game.stores;

import game.Actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionStore {

    private List<Actions> actionsList;

    public Map<Integer, Actions> toIntegerActionMap() {
        Map<Integer, Actions> result = new HashMap<>();
        actionsList.forEach(actions -> result.put(actions.getId(), actions));

        return result;
    }
}
