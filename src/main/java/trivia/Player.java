package trivia;

public class Player {
    private final String name;

    private int coins = 0;

    private boolean inJail = false;

    private int position = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public int getPosition() {
        return position;
    }

    public void moveForward(int positions) {
        this.position+=positions;
        if (this.position > GameBetter.NUMBER_OF_CATEGORIES * 3 - 1) {
            this.position-= (GameBetter.NUMBER_OF_CATEGORIES * 3);
        }
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
