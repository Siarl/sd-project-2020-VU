package game;

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
    public boolean onCommand(Command command, Callback callback) {
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
    public List<String> listCommands(Game game, List<String> addToThisList) {
        addToThisList.add("inventory");
        addToThisList.add("stats");
        return addToThisList;
    }

}
