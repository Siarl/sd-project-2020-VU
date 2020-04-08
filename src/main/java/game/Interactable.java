package game;

import java.util.List;

public interface Interactable {

    interface Callback {

        // TODO: 01-04-2020 replace with specific methods for events
        default void onMessage(String message) {}
    }

    boolean handleCommand(Command command, Callback callback);

    List<String> listHandledCommands(Game game);
}
