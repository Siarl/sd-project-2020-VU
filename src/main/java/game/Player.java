package game;

import java.util.List;

public class Player implements Interactable {

    private String name;
    private List<String> inventory;
    private PlayerStats playerStats;

    /*
    User Interaction Methods
     */

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (command.getAction().equals("inventory")) {

            StringBuilder inventoryString = new StringBuilder("Inventory:\n=============\n");
            ItemStore itemStore = command.getGame().getItemStore();

            for (String itemName : inventory) {
                inventoryString.append(itemStore.getItemByName(itemName).getName()).append("\n");
            }

            inventoryString.append("================");

            callback.onMessage(inventoryString.toString());

            return true;
        } else if (command.getAction().equals("stats")) {

            callback.onMessage(playerStats.toString());
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

    /*
    Getters & Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }
}
