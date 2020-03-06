package game;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Actions implements Interactable {

    private int id;
    private Map<String, Effect> effects;

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (hasCommand(command.getAction())) {
            Effect effect = getEffect(command.getAction());
            if (effect != null) {
                effect.apply(command.getGame());

                callback.onMessage("Effect applied... " + effect.toString());
            }
            return true;
        }

        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        Set<String> commands = getCommands();
        if (commands != null) {
            addToThisList.addAll(getCommands());
        }
        return addToThisList;
    }

    /*
    Getters - just wrappers for Map methods
     */

    public int getId() {
        return id;
    }

    public Effect getEffect(String command) {
        if (effects == null) return null;
        return effects.get(command);
    }

    public Set<String> getCommands() {
        if (effects == null) return null;
        return effects.keySet();
    }

    public boolean hasCommand(String command) {
        return effects != null && effects.containsKey(command);
    }
}
