package game;

public class CharacterStats extends Stats {

    public interface Listener {

        default void onDeath() {}

        default void onHealthChange(int oldHealth, int newHealth, int difference) {}

        default void onMaxHealthChange(int oldMaxHealth, int newMaxHealth, int difference) {}

        default void onBaseDamageChange(int oldBaseDamage, int newBaseDamage, int difference) {}

        default void onBaseLuckChange(int oldBaseLuck, int newBaseLuck, int difference) {}
    }

    private int minHealthPoints;
    private int maxHealthPoints;

    public CharacterStats(int maxHealthPoints, int baseDamage, int baseLuck) {
        super(maxHealthPoints, baseDamage, baseLuck);
        this.minHealthPoints = 0;
        this.maxHealthPoints = maxHealthPoints;
    }

    public void applyChange(Stats playerStats) {
        setHealthPoints(playerStats.healthPoints);
        setBaseLuck(playerStats.baseLuck);
        setBaseDamage(playerStats.baseDamage);
    }


    @Override
    public int getHealthPoints() {
        return super.getHealthPoints();
    }

    @Override
    public void setHealthPoints(int healthPoints) {
        setHealthPoints(healthPoints, null);
    }

    public void setHealthPoints(int healthPoints, Listener listener) {
        int oldHP = this.healthPoints;
        this.healthPoints += healthPoints;

        if (this.healthPoints > maxHealthPoints) {
            this.healthPoints = maxHealthPoints;
        } else if (this.healthPoints < minHealthPoints) {
            this.healthPoints = minHealthPoints;
            if (listener != null) listener.onDeath();
        }

        int newHP = this.healthPoints;
        if (listener != null) listener.onHealthChange(oldHP, newHP, newHP - oldHP);
    }


    @Override
    public int getBaseDamage() {
        return super.getBaseDamage();
    }

    @Override
    public void setBaseDamage(int baseDamage) {
        setBaseDamage(baseDamage, null);
    }

    public void setBaseDamage(int baseDamage, Listener listener) {
        int old = this.baseDamage;
        this.baseDamage += baseDamage;
        if (listener != null) listener.onBaseDamageChange(old, this.baseDamage, baseDamage);
    }

    @Override
    public void setBaseLuck(int baseLuck) {
        setBaseLuck(baseLuck, null);
    }

    @Override
    public int getBaseLuck() {
        return super.getBaseLuck();
    }

    public void setBaseLuck(int baseLuck, Listener listener) {
        int old = this.baseLuck;
        this.baseLuck += baseLuck;
        if (listener != null) listener.onBaseLuckChange(old, this.baseLuck, baseLuck);
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        setMaxHealthPoints(maxHealthPoints, null);
    }

    public void setMaxHealthPoints(int maxHealthPoints, Listener listener) {
        int old = this.maxHealthPoints;
        this.maxHealthPoints += maxHealthPoints;
        if (listener != null) listener.onMaxHealthChange(old, this.maxHealthPoints, maxHealthPoints);
    }

    public int getMinHealthPoints() {
        return minHealthPoints;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerStats{");
        sb.append("minHealthPoints=").append(minHealthPoints);
        sb.append(", healthPoints=").append(healthPoints);
        sb.append(", maxHealthPoints=").append(maxHealthPoints);
        sb.append(", baseDamage=").append(baseDamage);
        sb.append(", baseLuck=").append(baseLuck);
        sb.append('}');
        return sb.toString();
    }
}
