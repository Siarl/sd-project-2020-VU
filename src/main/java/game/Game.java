package game;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArraySet;

public class Game {

    private Map<Integer, Scene> sceneMap;
    private Map<Integer, Actions> actionsMap;
    private Map<String, Item> itemMap;
    private Player player;
    private int currentSceneId;
    private Stack<View> viewBackStack;
    private transient Set<Client> clients = new CopyOnWriteArraySet<>();

    public Game() {
        viewBackStack = new Stack<>();
    }

    public Game(String playerName, Map<Integer, Scene> sceneMap, Map<Integer, Actions> actionsMap, Map<String, Item> itemMap, int startSceneId) {
        this(sceneMap, actionsMap, itemMap, startSceneId);
        this.player = new Player(playerName);
    }

    public Game(Map<Integer, Scene> sceneMap, Map<Integer, Actions> actionsMap, Map<String, Item> itemMap, int startSceneId) {
        this();
        this.sceneMap = sceneMap;
        this.actionsMap = actionsMap;
        this.itemMap = itemMap;
        this.currentSceneId = startSceneId;
    }

    /*
    User Interaction Methods
     */

    // TODO: 02-03-2020 annotate 
    public void start(Client client) {

        addClient(client);

        clients.forEach(l->l.onMessage("You can write commands now!"));
    }

    // TODO: 02-03-2020 annotate
    public void handleCommand(String commandString) {
        Command command;
        try {
            command = new Command(this, commandString);
        } catch (Command.InvalidCommandException e) {
            clients.forEach(l->l.onMessage("Invalid command! " + e.getMessage()));
            return;
        }

        /*
        First check if player can handle this command
        Second check if current view can handle this command (scene also checks its items)
         */

        Interactable.Callback callback =  new Interactable.Callback() {
            @Override
            public void onMessage(String message) {
                clients.forEach(listener -> listener.onMessage(message));
            }
        };

        boolean isCommandHandled = player.onCommand(command, callback);

        if (!isCommandHandled) {
            isCommandHandled = viewBackStack.peek().onCommand(command, callback);
        }

        if (!isCommandHandled) {
            clients.forEach(l->l.onMessage("Unknown command!"));
        }

    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void enterView(View view, Interactable.Callback callback) {
        view.onEnter(this, callback);
        viewBackStack.push(view);
    }

    public void exitView(Interactable.Callback callback) {
        View view = viewBackStack.peek();
        view.onLeave(callback);
        viewBackStack.pop();
    }

    public View getCurrentView() {
        return viewBackStack.peek();
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCurrentSceneId() {
        return currentSceneId;
    }

    public void setCurrentSceneById(int id, Interactable.Callback callback) {
        if (sceneMap.containsKey(id)) {
            setCurrentSceneId(id, callback);
        } else {
            clients.forEach(listener -> listener.onMessage("Error: Scene does not exist"));
        }
    }

    private void setCurrentSceneId(int currentSceneId, Interactable.Callback callback) {
        // pop all views until a scene is reached
        while (!(viewBackStack.peek() instanceof Scene)) {
            exitView(callback);
        }

        exitView(callback); // once more to remove the scene
        this.currentSceneId = currentSceneId;
        enterView(sceneMap.get(currentSceneId), callback);
    }

}
