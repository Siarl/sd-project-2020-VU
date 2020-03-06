package game;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Actions extends HashMap<String, Effect> implements Interactable {

    private int id;

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (hasCommand(command.getAction())) {
            Effect effect = get(command.getAction());
            effect.apply(command.getGame());

            callback.onMessage("Effect applied... " + effect.toString());
            return true;
        }

        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        addToThisList.addAll(keySet());
        return addToThisList;
    }

    /*
    Getters - just wrappers for Map methods
     */

    public int getId() {
        return id;
    }

    public Effect getEffect(String command) {
        return get(command);
    }

    public Set<String> getCommands() {
        return keySet();
    }

    public boolean hasCommand(String command) {
        return containsKey(command);
    }
}
