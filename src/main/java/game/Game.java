package game;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArraySet;

public class Game {

    private transient Map<Integer, Scene> sceneMap;
    private transient Map<Integer, Actions> actionsMap;
    private transient Map<String, Item> itemMap;
    private transient Map<String, Character> characterMap;
    private transient Set<Client> clients = new CopyOnWriteArraySet<>();
    private Player player;
    private int currentSceneId;
    private Stack<View> viewBackStack;

    public Game() {
        viewBackStack = new Stack<>();
    }

    public Game(Map<Integer, Scene> sceneMap, Map<Integer, Actions> actionsMap, Map<String, Item> itemMap, Map<String, Character> characterMap, int startSceneId) {
        this();
        this.sceneMap = sceneMap;
        this.actionsMap = actionsMap;
        this.itemMap = itemMap;
        this.characterMap = characterMap;
        this.currentSceneId = startSceneId;
    }

    public Game(String playerName, Map<Integer, Scene> sceneMap, Map<Integer, Actions> actionsMap, Map<String, Item> itemMap, Map<String, Character> characterMap, int startSceneId) {
        this(sceneMap, actionsMap, itemMap, characterMap, startSceneId);
        this.player = new Player(playerName);
    }

    /*
    User Interaction Methods
     */

    // TODO: 02-03-2020 annotate 
    public void start(Client client) {

        addClient(client);

        enterView(sceneMap.get(currentSceneId), new Interactable.Callback() {
            @Override
            public void onMessage(String message) {
                clients.forEach(c -> c.onMessage(message));
            }
        });

        clients.forEach(l->l.onMessage("You can write commands now! Try: inspect"));
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
                clients.forEach(client -> client.onMessage(message));
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
        view.enter(this, callback);
        viewBackStack.push(view);
    }

    public void exitView(Interactable.Callback callback) {
        View view = viewBackStack.peek();
        view.exit(callback);
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

    public Map<String, Character> getCharacterMap() {
        return characterMap;
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
