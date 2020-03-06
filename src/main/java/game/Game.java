package game;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Game {

    // TODO: 02-03-2020 this is a PoC, should be replaced by more detailed and specific methods
    public interface Listener {

        void onMessage(String message);

    }

    private Map<Integer, Scene> sceneMap;
    private Map<Integer, Actions> actionsMap;
    private Map<String, Item> itemMap;
    private Player player;
    private int currentSceneId;

    public Game() {
        //NOP
    }

    public Game(String playerName, Map<Integer, Scene> sceneMap, Map<Integer, Actions> actionsMap, Map<String, Item> itemMap) {
        this.sceneMap = sceneMap;
        this.actionsMap = actionsMap;
        this.itemMap = itemMap;

        this.player = new Player(playerName);
    }

    public Game(Map<Integer, Scene> sceneMap, Map<Integer, Actions> actionsMap, Map<String, Item> itemMap) {
        this.sceneMap = sceneMap;
        this.actionsMap = actionsMap;
        this.itemMap = itemMap;
    }

    private transient Set<Listener> listeners = new CopyOnWriteArraySet<>();

    /*
    User Interaction Methods
     */

    // TODO: 02-03-2020 annotate 
    public void start(Listener listener) {

        subscribeListener(listener);

        listeners.forEach(l->l.onMessage("You can write commands now!"));
    }

    // TODO: 02-03-2020 annotate
    public void handleCommand(String commandString) {
        Command command;
        try {
            command = new Command(this, commandString);
        } catch (Command.InvalidCommandException e) {
            listeners.forEach(l->l.onMessage("Invalid command! " + e.getMessage()));
            return;
        }

        /*
        First check if player can handle this command
        Second check if scene can handle this command (scene also checks its items)
         */

        Interactable.Callback callback =  new Interactable.Callback() {
            @Override
            public void onMessage(String message) {
                listeners.forEach(listener -> listener.onMessage(message));
            }
        };

        boolean isCommandHandled = player.onCommand(command, callback);

        if (!isCommandHandled) {
            isCommandHandled = sceneMap.get(currentSceneId).onCommand(command, callback);
        }

        if (!isCommandHandled) {
            listeners.forEach(l->l.onMessage("Unknown command!"));
        }

    }

    public void subscribeListener(Listener listener) {
        listeners.add(listener);
    }

    public void unsubscribeListener(Listener listener) {
        listeners.remove(listener);
    }

    /*
    Getters & Setters
     */

    public Map<Integer, Scene> getSceneMap() {
        return sceneMap;
    }

    public Map<Integer, Actions> getActionsMap() {
        return actionsMap;
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentSceneId() {
        return currentSceneId;
    }

    public void setCurrentSceneById(int id) {
        if (sceneMap.containsKey(id)) {
            listeners.forEach(listener -> listener.onMessage("Error: Scene deos not exist"));
        } else {
            setCurrentSceneId(id);
        }
    }

    private void setCurrentSceneId(int currentSceneId) {
        listeners.forEach(listener -> listener.onMessage("Changed scenes."));
        this.currentSceneId = currentSceneId;
    }
}
