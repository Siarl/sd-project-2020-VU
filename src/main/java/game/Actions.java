package game;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Actions implements Interactable {

    private int id;
    private Map<String, Effects> commandEffectMap;

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (hasCommand(command.getAction())) {
            Effects effects = getEffects(command.getAction());
            if (effects != null) {
                effects.apply(command.getGame());

                callback.onMessage("Effect applied... " + effects.toString());
            }
            return true;
        }

        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        Set<String> commands = getCommandSet();
        if (commands != null) {
            addToThisList.addAll(getCommandSet());
        }
        return addToThisList;
    }

    /*
    Getters - just wrappers for Map methods
     */

    public int getId() {
        return id;
    }

    public Effects getEffects(String command) {
        if (commandEffectMap == null) return null;
        return commandEffectMap.get(command);
    }

    public Set<String> getCommandSet() {
        if (commandEffectMap == null) return null;
        return commandEffectMap.keySet();
    }

    public boolean hasCommand(String command) {
        return commandEffectMap != null && commandEffectMap.containsKey(command);
    }
}
