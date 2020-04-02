package game;

import java.util.List;

public class Effect {

    public enum Type {
        NAVIGATION,
        INVENTORY,
        STATS
    }

    private Type type;

    private String description;
    private int sceneIdChange;
    private Stats statsChange;
    private List<String> inventoryAddChange;
    private List<String> inventoryRemoveChange;

    public Effect(String description) {
        this.description = description;
    }

    public Effect(String description, int sceneIdChange) {
        this(description);
        this.type = Type.NAVIGATION;
        this.sceneIdChange = sceneIdChange;
    }

    public Effect(String description, Stats statsChange) {
        this(description);
        this.type = Type.STATS;
        this.statsChange = statsChange;
    }

    public Effect(String description, List<String> inventoryAddChange, List<String> inventoryRemoveChange) {
        this(description);
        this.type = Type.INVENTORY;
        this.inventoryAddChange = inventoryAddChange;
        this.inventoryRemoveChange = inventoryRemoveChange;
    }

    /**
     * Applies the changes specified effect to the game.
     * @param game the Game object to apply this effect on
     */
    public void apply(Game game, Interactable.Callback callback) {
        if (game == null) throw new IllegalArgumentException("game is null");

        switch (type) {
            case NAVIGATION:
                game.setCurrentSceneById(sceneIdChange, callback);
                break;
            case INVENTORY:
                game.getPlayer().getInventory().addAll(inventoryAddChange);
                game.getPlayer().getInventory().removeAll(inventoryRemoveChange);
                break;
            case STATS:
                game.getPlayer().getCharacterStats().applyChange(statsChange);
                break;
        }
    }

    /*
    Getters
     */

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public int getSceneIdChange() {
        return sceneIdChange;
    }

    public Stats getStatsChange() {
        return statsChange;
    }

    public List<String> getInventoryAddChange() {
        return inventoryAddChange;
    }

    public List<String> getInventoryRemoveChange() {
        return inventoryRemoveChange;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Effect{");
        sb.append("type=").append(type);
        sb.append(", description=").append(description);
        switch (type) {
            case NAVIGATION:
                sb.append(", sceneIdChange=").append(sceneIdChange);
                break;
            case STATS:
                sb.append(", statsChange=").append(statsChange);
                break;
            case INVENTORY:
                sb.append(", inventoryAddChange=").append(inventoryAddChange);
                sb.append(", inventoryRemoveChange=").append(inventoryRemoveChange);
                break;
        }
        sb.append('}');
        return sb.toString();
    }
}
