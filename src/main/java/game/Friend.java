package game;

import java.util.ArrayList;
import java.util.List;

public class Friend extends Character {

    private Conversation conversation;

    public Friend(String name, String description, int inventorySize) {
        super(name, description, inventorySize);
    }

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (command.getAction().equals("talk")) {
            conversation.onCommand(command, callback);
            return true;
        } else if (command.getAction().equals("inspect")) {
            callback.onMessage("inspect: friend " + name + ", " + conversation);
            return true;
        }
        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        List<String> result = new ArrayList<>();
        result.add("talk");
        return result;
    }

}
