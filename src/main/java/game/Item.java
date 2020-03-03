package game;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Item implements Interactable {

    private String name;
    private Actions actions;

    @Override
    public boolean onCommand(Command command, Callback callback) {

        if (command.hasReceiver() && command.getReceiver().equals(name)) {
            if (command.getAction().equals("inspect")) {
                StringBuilder message = new StringBuilder("inspect: item, name = ");
                message.append(name).append("\n==============");

                List<String> commands = listCommands(command.getGame(), new ArrayList<>());
                commands.forEach(s -> message.append(s).append("\n"));
                message.append("===============");

                callback.onMessage(message.toString());

            } else if (actions.hasCommand(command.getAction())) {
                Effect effect = actions.getEffect(command.getAction());
                effect.apply(command.getGame());
                callback.onMessage("Effect applied... " + effect.toString());
            } else {
                callback.onMessage("This command does not exist for this item.");
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        addToThisList.add("inspect " + name);

        Set<String> commands = actions.getCommands();
        commands.forEach(s -> s += " " + name);
        addToThisList.addAll(commands);

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

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }
}
