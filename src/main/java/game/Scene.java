package game;

import java.util.List;
import java.util.Map;

public class Scene extends View {

    private int id;
    private String name;
    private String description;
    private List<String> items;
    private Map<String, Integer> nextScenes;
    private Actions actions;

    /*
    User Interaction Methods
     */

    @Override
    void onEnter(Game game, Callback callback) {
        callback.onMessage("Entered scene: " + name);
        callback.onMessage(inspect(new Command(game, "inspect")));
    }

    @Override
    void onLeave(Callback callback) {
        //NOP
    }

    /**
     * onCommandEntered is called when the user enters a command and determines if the command can be handled by this
     * Scene.
     * @param command the {@link Command} in question
     * @param callback
     * @return true if command has been handled, false if not.
     */
    @Override
    public boolean onCommand(Command command, Callback callback) {

        if (command.getAction().equals("inspect")) {

            if (command.hasReceiver()) {
                if (nextScenes.containsKey(command.getReceiver())) {
                    command.getGame().setCurrentSceneById(nextScenes.get(command.getReceiver()), callback);
                } else if (items.contains(command.getReceiver())) {
                    Item item = command.getGame().getItemMap().get(command.getReceiver());
                    item.onCommand(command, callback);
                } else {
                    callback.onMessage("This item does not exist.");
                }

            } else {
                callback.onMessage(inspect(command));
            }

            return true;
        } else if (command.getAction().equals("search")) {

            callback.onMessage(search(command));
            return true;
        } else if (actions != null && !command.hasReceiver() && actions.hasCommand(command.getAction())) {
            // TODO: 04-03-2020 replace with Action.onCommand()
            Effects effects = actions.getEffects(command.getAction());
            if (effects != null) {

                effects.apply(command.getGame(), callback);

                callback.onMessage("Effect applied... " + effects.toString());
            }
            return true;
        }

        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        addToThisList.add("inspect");
        addToThisList.add("search");
        addToThisList.add("inspect <item>"); // not actually handled by Scene object
        addToThisList.add("inspect <north|easth|south|west");

        return addToThisList;
    }

    private String inspect(Command command) {
        StringBuilder message = new StringBuilder("inspect:\n");
        message.append(name).append("\n\n");
        message.append(description).append("\n\n");
        message.append("scene commands:\n");
        listCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));
        message.append("player commands:\n"); // TODO: 06-03-2020 handle listing player commands somewhere else, this is messy
        command.getGame().getPlayer()
                .listCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));
        message.append("\n\n");

        return message.toString();
    }

    private String search(Command command) {
        StringBuilder message = new StringBuilder("search:\n");
        message.append("You find the following items in the scene:\n");
        items.forEach(i -> message.append("\t").append(i).append("\n"));
        message.append("\n\n");
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
