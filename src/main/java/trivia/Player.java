package trivia;

public class Player {
    private final String name;

    private int coins = 0;

    private boolean inJail = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void putInJail() {
        this.inJail = true;
    }

    public void freeFromJail() {
        this.inJail = false;
    }

    public void addCoin() {
        this.coins++;
    }

    public boolean hasNotWonYet() {
        return this.coins < 6;
    }
}
