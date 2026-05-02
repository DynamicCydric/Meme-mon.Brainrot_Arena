import javax.swing.Timer;

public class BattleManager {
    public Pet player, bot;
    public boolean playerTurn = true;
    private Runnable updateUI;

    public BattleManager(Pet player, Pet bot, Runnable updateUI) {
        this.player = player;
        this.bot = bot;
        this.updateUI = updateUI;
    }

    public void playerAttack() {
        if (!playerTurn || player.hp <= 0 || bot.hp <= 0) return;

        playerTurn = false;
        player.state = 1;
        player.x += 50; // Move player 50 pixels toward the bot

        bot.takeDamage(player.aura);
        bot.state = 2;

        updateUI.run();

        // Reset position after a delay
        Timer resetPos = new Timer(500, e -> {
            player.x = player.baseX; // Snap back to start
            updateUI.run();
        });
        resetPos.setRepeats(false);
        resetPos.start();

        Timer timer = new Timer(1200, e -> executeBotTurn());
        timer.setRepeats(false);
        timer.start();
    }

    private void executeBotTurn() {
        if (bot.hp <= 0) return;

        bot.state = 1;
        player.takeDamage(bot.aura);
        player.state = 2;

        updateUI.run();

        // Return to Idle state
        Timer reset = new Timer(600, e -> {
            player.state = 0;
            bot.state = 0;
            playerTurn = true;
            updateUI.run();
        });
        reset.setRepeats(false);
        reset.start();
    }
}