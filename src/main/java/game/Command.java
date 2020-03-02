package game;

import java.util.Objects;

// TODO: 02-03-2020 annotate
public class Command {

    // TODO: 02-03-2020 annotate 
    public static class InvalidCommandException extends RuntimeException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    // example: open door, open is action and door is receiver

    private Game game;
    private String action;
    private String receiver;

    // TODO: 02-03-2020 annotate 
    /**
     * Constructor
     * @param game
     * @param command
     * @throws InvalidCommandException
     */
    public Command(Game game, String command) throws InvalidCommandException {
        if (game == null) throw new IllegalArgumentException("game is null");
        if (command == null) throw new IllegalArgumentException("command is null");

        this.game = game;

        String[] parts = command.split(" ");
        if (parts.length >= 1) {
            this.action = parts[0];
        } else {
            throw new InvalidCommandException("command does contain an action: " + command);
        }
        if (parts.length >= 2) {
            this.receiver = parts[1];
        } else {
            this.receiver = null;
        }
    }

    /*
    Getters & Setters
     */

    public Game getGame() {
        return game;
    }

    public String getAction() {
        return action;
    }

    public String getReceiver() {
        return receiver;
    }

    public boolean hasReceiver() {
        return receiver != null;
    }

}
