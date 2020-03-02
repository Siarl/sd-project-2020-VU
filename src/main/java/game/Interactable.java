package game;

import java.util.List;

public interface Interactable {

    interface Callback {
        default void onMessage(String message) {}
    }

    boolean onCommand(Command command, Callback callback);

    List<String> listCommands(Game game, List<String> addToThisList);
}
