package game;

import java.util.List;

public class Player extends Character {

    private PlayerStats playerStats;

    public Player(String name) {
        super(name, 12);
        playerStats = new PlayerStats();
        playerStats.setMaxHealthPoints(100);
        playerStats.setMinHealthPoints(0);
        playerStats.setHealthPoints(100);
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

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }
}
