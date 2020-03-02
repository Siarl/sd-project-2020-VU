package game;

public class PlayerStats extends Stats {

    private int minHealthPoints;
    private int maxHealthPoints;

    public void applyChange(Stats playerStats) {
        setHealthPoints(playerStats.healthPoints);
    }

    /*
    Getters & Setters
     */

    @Override
    public void setHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;

        if (this.healthPoints > maxHealthPoints) {
            this.healthPoints = maxHealthPoints;
        } else if (this.healthPoints < minHealthPoints) {
            this.healthPoints = minHealthPoints;
            // TODO: 02-03-2020 death event!
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerStats{");
        sb.append("minHealthPoints=").append(minHealthPoints);
        sb.append(", maxHealthPoints=").append(maxHealthPoints);
        sb.append('}');
        return sb.toString();
    }
}
