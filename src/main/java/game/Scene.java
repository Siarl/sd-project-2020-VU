package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Scene extends View {

    private int id;
    private String name;
    private String description;
    private List<String> items;
    private List<String> characters;
    private Map<String, Integer> nextScenes;
    private int actionsId;
    private transient Actions actions;

    public Scene(int id, String name, String description, List<String> items, List<String> characters, Map<String, Integer> nextScenes, int actionsId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
        this.characters = characters;
        this.nextScenes = nextScenes;
        this.actionsId = actionsId;
    }

    /*
    User Interaction Methods
     */

    @Override
    void onEnter(Game game, Callback callback) {
        if (actions == null) {
            actions = game.getActionsMap().get(actionsId);
        }
        callback.onMessage("Entered scene: " + name);
        callback.onMessage(inspect(new Command(game, "inspect")));
    }

    @Override
    void onLeave(Callback callback) {
        callback.onMessage("You leave " + name);
    }

    /**
     * onCommandEntered is called when the user enters a command and determines if the command can be handled by this
     * Scene.
     * @param command the {@link Command} in question
     * @param callback
     * @return true if command has been handled, false if not.
     */
    @Override
    public boolean handleCommand(Command command, Callback callback) {

        if (command.getAction().equals("inspect")) { // handles inspect command

            if (command.hasReceiver()) {
                if (characters.contains(command.getReceiver())) {
                    Character character = command.getGame().getCharacterMap().get(command.getReceiver());
                    if (character != null) {
                        character.handleCommand(command, callback);
                    }
                } else if (nextScenes.containsKey(command.getReceiver())) {
                    Integer next = nextScenes.get(command.getReceiver());
                    if (next != null) {
                        command.getGame().setCurrentSceneById(next, callback);
                    } else {
                        callback.onMessage("There's nothing to the " + command.getReceiver() + "...");
                    }
                } else if (items.contains(command.getReceiver())) {
                    Item item = command.getGame().getItemMap().get(command.getReceiver());
                    item.handleCommand(command, callback);
                } else {
                    callback.onMessage("This item does not exist.");
                }

            } else {
                callback.onMessage(inspect(command));
            }

            return true;
        } else if (command.getAction().equals("search")) { // handles search command

            callback.onMessage(search(command));
            return true;
        } else if (actions != null && !command.hasReceiver() && actions.hasCommand(command.getAction())) { // handles scene action
            // TODO: 04-03-2020 replace with Action.onCommand()
            Effects effects = actions.getEffects(command.getAction());
            if (effects != null) {

                effects.apply(command.getGame(), callback);

                callback.onMessage("Effect applied... " + effects.toString());
            }
            return true;
        } else if (command.hasReceiver() && command.getAction().equals("go")) { // handles next scene action
            if (nextScenes.containsKey(command.getReceiver())) {
                command.getGame().setCurrentSceneById(nextScenes.get(command.getReceiver()), callback);
            } else {
                callback.onMessage("This direction is not known...");
            }

            return true;
        } else if (command.hasReceiver() && items.contains(command.getReceiver())) { // handles item action
            return command.getGame().getItemMap().get(command.getReceiver()).handleCommand(command, callback);
        } else if (command.hasReceiver() && characters.contains(command.getReceiver())) { // handles character action
            return command.getGame().getCharacterMap().get(command.getReceiver()).handleCommand(command, callback);
        }

        return false;
    }

    @Override
    public List<String> listHandledCommands(Game game) {
        List<String> result = new ArrayList<>();
        result.add("search");
        result.add("inspect");
        result.add("inspect <item>"); // not actually handled by Scene object
        result.add("inspect <north|east|south|west");

        return result;
    }

    private String inspect(Command command) {
        StringBuilder message = new StringBuilder("inspect:\n");
        message.append(name).append("\n\n");
        message.append(description).append("\n\n");
        message.append("scene commands:\n");
        listHandledCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));
        message.append("player commands:\n"); // TODO: 06-03-2020 handle listing player commands somewhere else, this is messy
        command.getGame().getPlayer()
                .listHandledCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));

        return message.toString();
    }

    private String look(Command command) {
        StringBuilder message = new StringBuilder("look:\n");
        nextScenes.keySet().forEach(k -> message.append("\t").append(k).append(": ")
                .append(command.getGame().getSceneMap().get(k).name).append("\n"));
        return message.toString();
    }

    private String search(Command command) {
        StringBuilder message = new StringBuilder("search:\n");
        message.append("You find the following items in the scene:\n");
        items.forEach(i -> message.append("\t").append(i).append("\n"));
        message.append("And the following characters:\n");
        characters.forEach(c -> message.append("\t").append(c).append("\n"));
        return message.toString();
    }

    /*
    Getters
     */

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
