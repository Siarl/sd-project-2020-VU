package game;

import java.util.List;

// TODO: 25-03-2020 annotate
public class Effects {

    private List<Effect> effectList;

    // TODO: 25-03-2020 maybe make these apply methods and interface? Would that be good?
    public void apply(Game game) {
        effectList.forEach(e -> e.apply(game));
    }
}
