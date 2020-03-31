package game;

import java.util.List;

public class Conversation implements Interactable {

    private List<String> lines;
    private boolean hasTalked = false;

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (hasTalked) {
            callback.onMessage(lines.get(lines.size() - 1));
        } else {
            for (String line : lines) {
                callback.onMessage(line);
                try {
                    Thread.sleep(200);
                } catch (Exception ignored) {
                }
            }
        }
        return true;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        return null;
    }
}
