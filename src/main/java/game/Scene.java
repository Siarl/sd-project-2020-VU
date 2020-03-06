package game;

import java.util.List;

public class Scene implements Interactable {

    private int id;
    private String name;
    private String description;
    private List<String> items;
    private Actions actions;

    /*
    User Interaction Methods
     */

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

            StringBuilder message = new StringBuilder("inspect:\n");
            message.append(name).append("\n\n");
            message.append(description).append("\n\n");
            message.append("commands:\n");
            listCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));
            message.append("\n\n");

            callback.onMessage(message.toString());
            return true;
        } else if (command.getAction().equals("search")) {
            StringBuilder message = new StringBuilder("search:\n");
            message.append("You find the following items in the scene:\n");
            items.forEach(i -> message.append("\t").append(i).append("\n"));
            message.append("\n\n");

            callback.onMessage(message.toString());
            return true;
        } else if (actions.hasCommand(command.getAction())) {
            // TODO: 04-03-2020 replace with Action.onCommand()
            Effect effect = actions.get(command.getAction());
            effect.apply(command.getGame());

            callback.onMessage("Effect applied... " + effect.toString());
            return true;
        } else if (command.hasReceiver()) {
            if (items.contains(command.getReceiver())) {
                Item item = command.getGame().getItemMap().get(command.getReceiver());
                item.onCommand(command, callback);
            } else {
                callback.onMessage("This item does not exist.");
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


        return addToThisList;
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
