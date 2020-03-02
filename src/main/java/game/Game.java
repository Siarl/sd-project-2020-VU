package game;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Game {

    // TODO: 02-03-2020 this is a PoC, should be replaced by more detailed and specific methods
    public interface Listener {

        void onMessage(String message);

    }

    private SceneStore sceneStore;
    private ItemStore itemStore;
    private Player player;
    private int currentSceneId;
    private boolean isInitialized;

    private transient Set<Listener> listeners = new CopyOnWriteArraySet<>();

    /*
    User Interaction Methods
     */

    // TODO: 02-03-2020 annotate 
    public void start(Listener listener) {
        if (!isInitialized) {
            initialize();
        }

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
            isCommandHandled = sceneStore.getSceneById(currentSceneId).onCommand(command, callback);
        }

        if (!isCommandHandled) {
            listeners.forEach(l->l.onMessage("Unknown command!"));
        }

    }

    // TODO: 02-03-2020 annotate
    private void initialize() {
        // TODO: 02-03-2020 do some stuff here i guess
        isInitialized = true;
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

    public SceneStore getSceneStore() {
        return sceneStore;
    }

    public void setSceneStore(SceneStore sceneStore) {
        this.sceneStore = sceneStore;
    }

    public ItemStore getItemStore() {
        return itemStore;
    }

    public void setItemStore(ItemStore itemStore) {
        this.itemStore = itemStore;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCurrentSceneId() {
        return currentSceneId;
    }

    public void setCurrentSceneId(int currentSceneId) {
        listeners.forEach(listener -> listener.onMessage("Changed scenes."));
        this.currentSceneId = currentSceneId;
    }

    public void setCurrentSceneById(int id) {
        if (sceneStore.hasScene(id)) {
            listeners.forEach(listener -> listener.onMessage("Error: Scene deos not exist"));
        } else {
            setCurrentSceneId(id);
        }
    }
}
