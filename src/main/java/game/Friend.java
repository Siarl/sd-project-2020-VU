package game;

import java.util.ArrayList;
import java.util.List;

public class Friend extends Character {

    private Conversation conversation;

    public Friend(String name, String description, int inventorySize, Conversation conversation) {
        super(name, description, inventorySize, null);
        this.conversation = conversation;
    }

    @Override
    public boolean handleCommand(Command command, Callback callback) {
        if (command.getAction().equals("talk")) {
            conversation.handleCommand(command, callback);
            return true;
        } else if (command.getAction().equals("inspect")) {
            StringBuilder message = new StringBuilder("inspect: friend " + name + ", " + description + "\n");
            listHandledCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));
            callback.onMessage(message.toString());

            return true;
        }
        return false;
    }

    @Override
    public List<String> listHandledCommands(Game game) {
        List<String> result = new ArrayList<>();
        result.add("inspect");
        result.add("talk");
        return result;
    }

}
