package game;

import com.kotcrab.annotation.CallSuper;

import java.util.List;

public abstract class View implements Interactable {

    private Game game;

    abstract void onEnter(Game game, Callback callback);

    abstract void onLeave(Callback callback);

    protected void enter(Game game, Callback callback) {
        this.game = game;

        this.onEnter(game, callback);
    }

    protected void exit(Callback callback) {
        this.game = null;

        this.onLeave(callback);
    }

    protected Game getGame() {
        return this.game;
    }
}
