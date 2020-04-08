package game;

public class Stats {

    protected int healthPoints;
    protected int baseDamage;
    protected int baseLuck;

    public Stats(int healthPoints, int baseDamage, int baseLuck) {
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;
        this.baseLuck = baseLuck;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getBaseLuck() {
        return baseLuck;
    }

    public void setBaseLuck(int baseLuck) {
        this.baseLuck = baseLuck;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Stats{");
        sb.append("healthPoints=").append(healthPoints);
        sb.append("baseDamage=").append(baseDamage);
        sb.append("baseLuck=").append(baseLuck);
        sb.append('}');
        return sb.toString();
    }
}
