package game;

import java.util.List;

// TODO: 25-03-2020 annotate
public class Effects {

    private List<Effect> effectList;

    // TODO: 25-03-2020 maybe make these apply methods and interface? Would that be good?
    public void apply(Game game, Interactable.Callback callback) {
        effectList.forEach(e -> e.apply(game, callback));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Effects{");
        effectList.forEach(effect -> sb.append(effect.toString()));
        sb.append('}');
        return sb.toString();
    }
}
