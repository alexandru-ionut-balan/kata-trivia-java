package trivia;

public class Player {
    private final String name;

    private int coins = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoin() {
        this.coins++;
    }
}
