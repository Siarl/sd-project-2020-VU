package game;

import java.util.List;

public class Enemy extends Character {

    public Enemy(String name, String description, int inventorySize) {
        super(name, description, inventorySize);
    }

    @Override
    public boolean onCommand(Command command, Callback callback) {
        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        return null;
    }
}
