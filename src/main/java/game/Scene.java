package game;

import java.util.ArrayList;
import java.util.List;

public class Scene implements Interactable {

    private int id;
    private String name;
    private List<String> items;

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

            StringBuilder message = new StringBuilder("inspect: scene, name = " + name + "\n items: \n");
            for (String item : items) {
                message.append(item).append("\n");
            }
            message.append("==========");

            callback.onMessage(message.toString());

            return true;
        } else if (command.hasReceiver()) {
            if (items.contains(command.getReceiver())) {
                Item item = command.getGame().getItemStore().getItemByName(command.getReceiver());
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
        addToThisList.add("inspect <item>"); // not actually handled by Scene object


        return addToThisList;
    }

    /*
    Getters & Setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
