package game;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Battle extends View {

    private Player player;
    private Enemy enemy;
    private Character winner;

    private static Map<String, String> whoBeatsWhoMap = new HashMap<>() {
        {
            putIfAbsent("rock", "scissors");
            putIfAbsent("scissors", "paper");
            putIfAbsent("paper", "rock");
        }
    };

    public Battle (Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    void onEnter(Game game, Callback callback) {
        callback.onMessage("You enter the fight with Enemy " + enemy.getName());
        callback.onMessage("Pick rock, paper or scissors!");
    }

    @Override
    void onLeave(Callback callback) {
        if (winner != null && winner == player) {
            callback.onMessage("You won the fight.");
        }
    }

    @Override
    public boolean onCommand(Command command, Callback callback) {
        if (command.getAction().equals("rock")
                || command.getAction().equals("paper")
                || command.getAction().equals("scissors")) {
            challenge(command.getAction(), callback);
        }
        return false;
    }

    @Override
    public List<String> listCommands(Game game, List<String> addToThisList) {
        addToThisList.add("rock");
        addToThisList.add("paper");
        addToThisList.add("scissors");
        return null;
    }

    private void challenge(String playerChoice, Callback callback) {
        UniformRandomProvider rng = RandomSource.create(RandomSource.MT);
        Character winner, loser;
        String enemyChoice;

        if (rng.nextBoolean()) {
            winner = player;
            loser = enemy;
            enemyChoice = whoBeatsWhoMap.get(playerChoice);
        } else {
            winner = enemy;
            loser = player;
            enemyChoice = whoBeatsWhoMap.get(whoBeatsWhoMap.get(playerChoice));
        }

        int damage = winner.getCharacterStats().getBaseDamage();
        int hp = loser.getCharacterStats().getHealthPoints();
        loser.getCharacterStats().setHealthPoints(-damage);

        callback.onMessage(player.getName() + " picks " + playerChoice + ", " + enemy.getName() + " picks " + enemyChoice);
        callback.onMessage(winner.getName() + " won that round!");
        callback.onMessage(loser.getName() + ": HP = " + loser.getCharacterStats().getHealthPoints());
    }
}
