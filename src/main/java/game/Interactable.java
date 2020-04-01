package game;

import java.util.ArrayList;
import java.util.List;

public interface Interactable {

    interface Callback {

        // TODO: 01-04-2020 replace with specific methods for events
        default void onMessage(String message) {}
    }

    boolean onCommand(Command command, Callback callback);

    List<String> listCommands(Game game, List<String> addToThisList);

    default List<String> listCommands(Game game) {
        return listCommands(game, new ArrayList<>());
    }
}
