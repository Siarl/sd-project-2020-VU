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
        sb.append(", healthPoints=").append(healthPoints);
        sb.append(", maxHealthPoints=").append(maxHealthPoints);
        sb.append('}');
        return sb.toString();
    }

    public int getMinHealthPoints() {
        return minHealthPoints;
    }

    public void setMinHealthPoints(int minHealthPoints) {
        this.minHealthPoints = minHealthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }
}
