package game;

import java.util.ArrayList;
import java.util.List;

public interface Interactable {

    interface Callback {
        default void onMessage(String message) {}
    }

    boolean onCommand(Command command, Callback callback);

    List<String> listCommands(Game game, List<String> addToThisList);

    default List<String> listCommands(Game game) {
        return listCommands(game, new ArrayList<>());
    }
}
