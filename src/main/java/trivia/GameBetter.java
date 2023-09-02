package trivia;

import java.util.ArrayList;

import static trivia.Category.*;

// REFACTOR ME
public class GameBetter implements IGame {
    private final ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private Player currentPlayer = null;

    public static final int NUMBER_OF_CATEGORIES = 4;
    public static final int NUMBER_OF_QUESTIONS_PER_CATEGORY = 50;

    public GameBetter() {
        Category.ALL_CATEGORIES.forEach(category -> Decks.generateDeck(category, NUMBER_OF_QUESTIONS_PER_CATEGORY));
    }

    public void add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());

        if (currentPlayer == null) {
            currentPlayer = players.get(0);
        }
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!currentPlayer.isInJail()) {
            movePlayerForward(roll);
            askQuestion();
            return;
        }

        if (roll % 2 != 0) {
            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            currentPlayer.freeFromJail();
            movePlayerForward(roll);
            askQuestion();
        } else {
            System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
        }
    }

    private void movePlayerForward(int roll) {
        currentPlayer.moveForward(roll);
        System.out.println(currentPlayer.getName() + "'s new location is " + currentPlayer.getPosition());
    }

    private void askQuestion() {
        System.out.println("The category is " + Category.getCategoryForPosition(currentPlayer.getPosition()).getName());

        switch (Category.getCategoryForPosition(currentPlayer.getPosition())) {
            case POP -> System.out.println(Decks.removeQuestionFromDeck(POP).getQuestionContent());
            case SCIENCE -> System.out.println(Decks.removeQuestionFromDeck(SCIENCE).getQuestionContent());
            case SPORTS -> System.out.println(Decks.removeQuestionFromDeck(SPORTS).getQuestionContent());
            case ROCK -> System.out.println(Decks.removeQuestionFromDeck(ROCK).getQuestionContent());
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInJail()) {
            nextTurn();
            return true;
        }

        System.out.println("Answer was correct!!!!");
        currentPlayer.addCoin();
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getCoins() + " Gold Coins.");

        boolean winner = currentPlayer.hasNotWonYet();
        nextTurn();

        return winner;
    }

    private void nextTurn() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");

        currentPlayer.putInJail();
        nextTurn();

        return true;
    }
}
