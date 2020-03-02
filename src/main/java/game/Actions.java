package game;

import java.util.Map;
import java.util.Set;

public class Actions {

    private int id;
    private Map<String, Effect> actions;

    public int getId() {
        return id;
    }

    public Effect getEffect(String command) {
        return actions.get(command);
    }

    public Set<String> getCommands() {
        return actions.keySet();
    }

    public boolean hasCommand(String command) {
        return actions.containsKey(command);
    }
}
