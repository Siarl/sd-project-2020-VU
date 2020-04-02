package game;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {

    public Player(String name) {
        super(name, "", 12, null); //TODO replace null
        stats = new CharacterStats(100, 20, 1);
    }

    /*
    User Interaction Methods
     */

    @Override
    public boolean handleCommand(Command command, Callback callback) {
        if (command.getAction().equals("inventory")) {

            StringBuilder inventoryString = new StringBuilder("Inventory:\n=============\n");

            for (String itemName : inventory) {
                inventoryString.append("\t").append(itemName).append("\n");
            }

            inventoryString.append("================");

            callback.onMessage(inventoryString.toString());

            return true;
        } else if (command.getAction().equals("stats")) {

            callback.onMessage(stats.toString());
            return true;
        }

        return false;
    }

    @Override
    public List<String> listHandledCommands(Game game) {
        List<String> result = new ArrayList<>();
        result.add("inventory");
        result.add("stats");
        return result;
    }

}
