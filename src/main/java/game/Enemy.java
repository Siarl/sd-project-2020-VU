package game;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Character {

    public Enemy(String name, String description, int inventorySize, CharacterStats characterStats) {
        super(name, description, inventorySize, characterStats);
    }

    @Override
    public boolean handleCommand(Command command, Callback callback) {
        if (command.getAction().equals("fight")) {
            Battle battle = new Battle(command.getGame().getPlayer(), this);
            command.getGame().enterView(battle, callback);
            return true;
        } else if (command.getAction().equals("inspect")) {
            StringBuilder message = new StringBuilder("inspect enemy " + name + ": " + description + "\n");
            listHandledCommands(command.getGame()).forEach(s -> message.append("\t").append(s).append("\n"));
            callback.onMessage(message.toString());
            return true;
        }
        return false;
    }

    @Override
    public List<String> listHandledCommands(Game game) {
        List<String> result = new ArrayList<>();
        result.add("fight");
        result.add("inspect");
        return result;
    }
}
