package game;

import java.util.List;

// TODO: 28-03-2020 extend View and rework some parts
public class Conversation implements Interactable {

    private List<String> lines;
    private boolean hasTalked;

    public Conversation(List<String> lines) {
        this.lines = lines;
        this.hasTalked = false;
    }

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (hasTalked) {
            callback.onMessage(lines.get(lines.size() - 1));
        } else {
            for (String line : lines) {
                callback.onMessage(line);
                try {
                    Thread.sleep(1000);
                } catch (Exception ignored) {
                }
            }
            this.hasTalked = true;
        }
        return true;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        return null;
    }
}
