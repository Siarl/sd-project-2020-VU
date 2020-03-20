package game;

public class Stats {

    protected int healthPoints;

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stats{");
        sb.append("healthPoints=").append(healthPoints);
        sb.append('}');
        return sb.toString();
    }
}
